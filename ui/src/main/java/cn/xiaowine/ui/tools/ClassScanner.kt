package cn.xiaowine.ui.tools

import android.content.Context
import cn.xiaowine.ui.WinePage
import java.util.*

object ClassScanner {

    @Suppress("UNCHECKED_CAST")
    fun scanPages(context: Context, packageName: String): List<Class<*>> {
        val classLoader = context.classLoader
        return runCatching {
            val dexPathListField = classLoader.javaClass.superclass?.getDeclaredField("pathList")
            dexPathListField?.isAccessible = true
            val dexPathList = dexPathListField?.get(classLoader)
            val dexElementsField = dexPathList?.javaClass?.getDeclaredField("dexElements")
            dexElementsField?.isAccessible = true
            val dexElements = dexElementsField?.get(dexPathList) as? Array<*>

            dexElements?.flatMap { element ->
                val dexFileField = element?.javaClass?.getDeclaredField("dexFile")
                dexFileField?.isAccessible = true
                val dexFile = dexFileField?.get(element)

                val entriesMethod = dexFile?.javaClass?.getDeclaredMethod("entries")
                val entries = entriesMethod?.invoke(dexFile) as? Enumeration<String>

                entries?.toList()?.filter { it.startsWith(packageName) }?.mapNotNull { entry ->
                    runCatching {
                        val entryClass = Class.forName(entry, true, classLoader)
                        if (entryClass.name.contains("$") || !WinePage::class.java.isAssignableFrom(entryClass)) {
                            null
                        } else {
                            entryClass
                        }
                    }.getOrNull()
                } ?: emptyList()
            }?.distinct() ?: emptyList()
        }.getOrElse {
            it.printStackTrace()
            emptyList()
        }
    }
}