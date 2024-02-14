#这是什么
![](https://cia.hyperos.mi.com/hyperos-homepage/light_hyperos.svg)
###这是一个模仿HyperOS风格制作的DSL界面库
适合快速编写模仿HyperOS风格的软件/模块
本库一切行为，与小米无关

## 已经实现可快速使用的组件

- [x] 标题 (title)
- [x] 文本 (text)
- [x] 分割线 (line)
- [x] 按钮 (button)
- [x] 开关 (switch)
- [x] 拖动条 (seekbar)
- [x] 对话框 (dialog)
- [x] 卡片 (card)
- [x] 自定义组件 (custom)
  具体可查看 cn/xiaowine/ui/build/PageBuild.kt

#如何使用

### 1. 项目 Gradle 添加 JitPack 依赖

groovy

```
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

kotlin

```kotlin
allprojects {
    repositories {
        // ...
        maven("https://jitpack.io")
    }
}
```

### 2. 要使用的模块下添加 DSP 依赖

最新版本⬇️⬇️⬇️

[![](https://jitpack.io/v/xiaowine/WineUI.svg)](https://jitpack.io/#xiaowine/WineUI/)
---
groovy

```groovy
dependencies {
    // ...
    implementation 'com.github.xiaowine:WineUI:<VERSION>'
}
```

kotlin

```kotlin
dependencies {
    // ...
    implementation("com.github.xiaowine:WineUI:<VERSION>")
}
```

### 3. 修改清单文件

```xml

<manifest>
    <application android:theme="@style/Theme.WineUI">
        <!--     将主题设置为@style/Theme.WineUI       -->
        <!--     不建议单独设置activity主题       -->
    </application>
</manifest>
```

### 4. 继承WineActivity，并注册Page

```kotlin
// 1.继承WineActivity
// 2.注册页面registerPage
// 3.注册页面的时候，PageData参数可以设置isHome为true，表示这个页面是首页
// 首页在tabbar不会有返回按钮，其余页面都会有返回按钮
class DemoActivity : WineActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPage(
            PageData(MainPage::class.java, isHome = true),
            PageData(TextPage::class.java),
            PageData(SwitchPage::class.java)
        )
//        或通过扫描包名注册页面（全部按照默认配置生成界面），两种方式可以混用，后注册的覆盖先注册的
        registerPage("cn.xiaowine.app.pages", MainPage::class.java)
    }
}
```

### 5. 创建页面

```kotlin
// 1.继承WinePage
// 2.初始化页面initPage
// 并非必须在init中初始化页面，可以在除了onCreateView和onViewCreated任何地方初始化页面，只需要加上一个reloadPage()方法
// 可参考 cn/xiaowine/ui/pages/MainPage.kt

// 加上@Coroutine注解，初始化页面的时候会采用协程
@Coroutine
class MainPage : WinePage() {
    init {
        initPage {
            title {
                text = this@MainPage::class.java.simpleName
            }
            title {
                text = "标题"
            }
            toPageText(page = TextPage::class.java)
            toPageText(page = SwitchPage::class.java)
            toPageText(page = SeeKBarPage::class.java)
            toPageText(page = DialogPage::class.java)
            toPageText(page = CustomPage::class.java)
            toPageText(page = CardPage::class.java)
        }
    }
}
```

### !!!注意，如果你开启了混淆，请添加以下规则

```pro
-keep class cn.xiaowine.ui.annotation.** { *; }
-keep class cn.xiaowine.ui.data.** { *; }
-keep class cn.xiaowine.ui.widget.**{ <init>(...);}
```