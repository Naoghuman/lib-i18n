Lib-I18n
===

[![Build Status](https://travis-ci.org/Naoghuman/lib-i18n.svg?branch=master)](https://travis-ci.org/Naoghuman/lib-i18n)
[![license: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub release](https://img.shields.io/github/release/Naoghuman/lib-i18n.svg)](https://GitHub.com/Naoghuman/lib-i18n/releases/)



Intention
---

The library `Lib-I18N` allowed the developer to bind easly `.properties` key (values) 
to a [StringBinding]. So changing the language during runtime in a [JavaFX] application 
won't be a problem anymore.  
Lib-I18N is written in JavaFX, [Maven] and [NetBeans].

A demo application which shows the features from this library can be found under 
[App-I18N-Demo] (which is currently under development).
* TODO Add screenshoot.


Content
---

* [Examples](#Examples)
    - [How to use the builder I18NResourceBundleBuilder](#HoToUsReBuBu)
    - [How to use the builder I18NBindingBuilder](#HoToUsBiBu)
    - [How to use the builder I18NMessageBuilder](#HoToUsMeBu)
* [JavaDoc](#JavaDoc)
* [Download](#Download)
* [Requirements](#Requirements)
* [Installation](#Installation)
* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



Examples<a name="Examples" />
---

### How to use the builder I18NResourceBundleBuilder<a name="HoToUsReBuBu" />

With the builder [I18NResourceBundleBuilder] the developer can configure the 
[ResourceBundle] which contains the `key - value` terms which will then be bind 
to a [Locale]. That means switching the `actual` Locale update all binded textes 
with the specific value from the corresponding language `.properties` file.

#### Specification: _Usage of I18NResourceBundleBuilder_
```java
/**
 * 1) Starts the configuration process.
 * 2) Defines the path and name from the .properties file.
 * 3) Sets all supported Locales with an [].
 * 4) Sets all supported Locales with an ObservableList.
 * 5) Sets the default Locale.
 * 6) Sets the actual Locale.
 * 7) Completes the configuration process.
 */
I18NResourceBundleBuilder.configure() // 1
        .baseName(String)             // 2
        .supportedLocales(Locale...)  // 3
        .supportedLocales(ObservableList<Locale>) // 4
        .defaultLocale(Locale)        // 5
        .actualLocale(Locale)         // 6
        .build();                     // 7
```

#### Examples:
```java
@Test
public void lastStepWithSupportedLocalesAsArray() {
    String resourcbundle = "com.github.naoghuman.lib.i18n.internal.resourcebundle";
    I18NResourceBundleBuilder.configure()
            .baseBundleName(resourcbundle)
            .supportedLocales(Locale.ITALIAN, Locale.JAPANESE)
            .defaultLocale(Locale.ITALIAN)
            .actualLocale(Locale.JAPANESE)
            .build();

    assertEquals(resourcbundle,   I18NFacade.getDefault().getBaseBundleName());
    assertEquals(Locale.ITALIAN,  I18NFacade.getDefault().getDefaultLocale());
    assertEquals(Locale.JAPANESE, I18NFacade.getDefault().getActualLocale());
    assertEquals(2,               I18NFacade.getDefault().getSupportedLocales().size());
}

@Test
public void lastStepWithSupportedLocalesAsObservableList() {
    String resourcbundle = "com.github.naoghuman.lib.i18n.internal.resourcebundle";
    final ObservableList<Locale> locales = FXCollections.observableArrayList();
    locales.addAll(Locale.ITALIAN, Locale.JAPANESE, Locale.FRENCH);
    I18NResourceBundleBuilder.configure()
            .baseBundleName(resourcbundle)
            .supportedLocales(locales)
            .defaultLocale(Locale.ITALIAN)
            .actualLocale(Locale.JAPANESE)
            .build();

    assertEquals(resourcbundle,  I18NFacade.getDefault().getBaseBundleName());
    assertEquals(Locale.ITALIAN, I18NFacade.getDefault().getDefaultLocale());
    assertEquals(Locale.JAPANESE,I18NFacade.getDefault().getActualLocale());
    assertEquals(3,              I18NFacade.getDefault().getSupportedLocales().size());
}
```

### How to use the builder I18NBindingBuilder<a name="HoToUsBiBu" />

The builder [I18NBindingBuilder] let the developer create a [StringBinding]. The 
StringBinding can created with a function from type [Callable&lt;String&gt;] or 
with a .properties `key` and optional `arguments`.

#### Specification: _Usage of I18NBindingBuilder_
```java
/**
 * 1) Starts the binding process.
 * 2) Use the given function to create a StringBinding.
 * 3) Completes the binding process and returns the StringBinding.
 */
I18NBindingBuilder.bind()          // 1
       .callable(Callable<String>) // 2
       .build();                   // 3

/**
 * 1) Starts the binding process.
 * 2) Defines the key which value will be bind to the StringBinding.
 * 3) Optional arguments for the value from the given key.
 * 4) Completes the binding process and returns the StringBinding.
 */
I18NBindingBuilder.bind()         // 1
       .key(String)               // 2
       .arguments(Object... args) // 3
       .build();                  // 4
```

#### Examples:
```java
@Test
public void lastStepCallable() {
    I18NResourceBundleBuilder.configure()
            .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
            .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
            .defaultLocale(Locale.ENGLISH)
            .actualLocale(Locale.GERMAN)
            .build();

    Optional<StringBinding> result = I18NBindingBuilder.bind()
            .callable(() -> I18NMessageBuilder.message()
                    .key("resourcebundle.title")
                    .build()
            )
            .build();
    assertTrue(result.isPresent());
    assertEquals("RB: Test Titel", result.get().get());

    I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
    assertEquals("RB: Test title", result.get().get());
}

@Test
public void lastStepKeyWithoutArguments() {
    I18NResourceBundleBuilder.configure()
            .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
            .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
            .defaultLocale(Locale.ENGLISH)
            .actualLocale(Locale.GERMAN)
            .build();

    Optional<StringBinding> result = I18NBindingBuilder.bind()
            .key("resourcebundle.title")
            .build();
    assertTrue(result.isPresent());
    assertEquals("RB: Test Titel", result.get().get());

    I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
    assertEquals("RB: Test title", result.get().get());
}

@Test
public void lastStepKeyWithArguments() {
    I18NResourceBundleBuilder.configure()
            .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
            .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
            .defaultLocale(Locale.ENGLISH)
            .actualLocale(Locale.GERMAN)
            .build();

    Optional<StringBinding> result = I18NBindingBuilder.bind()
            .key("resourcebundle.label.with.parameter")
            .arguments(123)
            .build();
    assertTrue(result.isPresent());
    assertEquals("RB: Text mit Parameter: 123", result.get().get());

    I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
    assertEquals("RB: Text with parameter: 123", result.get().get());
}
```

### How to use the builder I18NMessageBuilder<a name="HoToUsMeBu" />

To load a .properties `key` with optional `arguments` from the initialized [ResourceBundle] 
through the [I18NResourceBundleBuilder] the developer can use the builder [I18NMessageBuilder].

#### Specification: _Usage of I18NMessageBuilder_
```java
/**
 * 1) Starts the message process.
 * 2) Defines the key which value will be loaded.
 * 3) Optional arguments for the value from the given key.
 * 4) Completes the message process and returns a String.
 */
I18NMessageBuilder.message()  // 1
        .key(String)          // 2
        .arguments(Object...) // 3
        .build();             // 4
```

#### Examples:
```java
@Test
public void lastStepWithoutArguments() {
    I18NResourceBundleBuilder.configure()
            .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
            .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
            .defaultLocale(Locale.ENGLISH)
            .actualLocale(Locale.GERMAN)
            .build();

    String result = I18NMessageBuilder.message()
            .key("resourcebundle.title")
            .build();
    assertEquals("RB: Test Titel", result);

    I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
    result = I18NMessageBuilder.message()
            .key("resourcebundle.title")
            .build();
    assertEquals("RB: Test title", result);
}
    
@Test
public void lastStepWithArguments() {
    I18NResourceBundleBuilder.configure()
            .baseBundleName("com.github.naoghuman.lib.i18n.internal.resourcebundle")
            .supportedLocales(Locale.ENGLISH, Locale.GERMAN)
            .defaultLocale(Locale.ENGLISH)
            .actualLocale(Locale.GERMAN)
            .build();

    String result = I18NMessageBuilder.message()
            .key("resourcebundle.label.with.parameter")
            .arguments(2)
            .build();
    assertEquals("RB: Text mit Parameter: 2", result);

    I18NFacade.getDefault().setActualLocale(Locale.ENGLISH);
    result = I18NMessageBuilder.message()
            .key("resourcebundle.label.with.parameter")
            .arguments(123)
            .build();
    assertEquals("RB: Text with parameter: 123", result);
}
```



JavaDoc<a name="JavaDoc" />
---

The [JavaDoc] from the library `Lib-I18N` can be explored here: [JavaDoc Lib-I18N v0.6.1]

_Image:_ JavaDoc Lib-I18N v0.6.1  
![Lib-I18N_JavaDoc_v0.6.1_2018-12-08_13-49.png][Lib-I18N_JavaDoc_v0.6.1_2018-12-08_13-49]



Download<a name="Download" />
---

Current `version` is `0.6.1`. Main points in this release are:
* Update the `How to use the builder...` sections.
* `JavaDoc` from the library is now online available: http://naoghuman.github.io/lib-i18n/apidocs

**Maven coordinates**  
In context from a [Maven] project you can use following maven coordinates: 
```xml
<dependencies>
    <dependency>
        <groupId>com.github.naoghuman</groupId>
        <artifactId>lib-i18n</artifactId>
        <version>0.6.1</version>
    </dependency>
</dependencies>
```

Download:
* [Release v0.6.1] (12.08.2018 / MM.dd.yyyy)

An overview about all existings releases can be found here:
* [Overview] from all releases in `Lib-I18N`.



Requirements<a name="Requirements" />
---

* On your system you need [JRE 8] or [JDK 8] installed.
* The library [lib-i18n-0.6.0.jar](#Installation).

In the library are following libraries registered as dependencies:
* The library [lib-logger-0.6.0.jar](#Installation).
  * Included in `Lib-Logger` is the library [log4j-api-2.10.0.jar].
  * Included is `Lib-Logger` is the library [log4j-core-2.10.0.jar].



Installation<a name="Installation" />
---

##### Install the project in your preferred IDE

* If not installed download the [JRE 8] or the [JDK 8].
    - Optional: To work better with [FXML] files in a [JavaFX] application 
      download the JavaFX [Scene Builder] supported by `Gluon`.
* Choose your preferred IDE (e.g. [NetBeans], [Eclipse] or [IntelliJ IDEA]) for development.
* Download or clone [Lib-Logger].
* Open the projects in your IDE and run them.



Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you could report an [Issue].
* If you want to contribute to the project plz fork the project and do a [Pull Request].



License<a name="License" />
---

The project `Lib-I18n` is licensed under [General Public License 3.0].



Autor<a name="Autor" />
---

The project `Lib-I18n` is maintained by me, Peter Rogge. See [Contact](#Contact).



Contact<a name="Contact" />
---

You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Images)
[Lib-I18N_JavaDoc_v0.6.1_2018-12-08_13-49]:https://user-images.githubusercontent.com/8161815/49686122-40bca500-faf0-11e8-866f-f1c76714883b.png



[//]: # (Links)
[App-I18N-Demo]:https://github.com/Naoghuman/app-i18n-demo
[Callable&lt;String&gt;]:https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/Callable.html
[Eclipse]:https://www.eclipse.org/
[FXML]:http://docs.oracle.com/javafx/2/fxml_get_started/jfxpub-fxml_get_started.htm
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[I18NBindingBuilder]:https://github.com/Naoghuman/lib-i18n/blob/master/src/main/java/com/github/naoghuman/lib/i18n/core/I18NBindingBuilder.java
[I18NMessageBuilder]:https://github.com/Naoghuman/lib-i18n/blob/master/src/main/java/com/github/naoghuman/lib/i18n/core/I18NMessageBuilder.java
[I18NResourceBundleBuilder]:https://github.com/Naoghuman/lib-i18n/blob/master/src/main/java/com/github/naoghuman/lib/i18n/core/I18NResourceBundleBuilder.java
[IntelliJ IDEA]:http://www.jetbrains.com/idea/
[Issue]:https://github.com/Naoghuman/lib-i18n/issues
[JavaDoc]:http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html
[JavaDoc Lib-I18N v0.6.1]:http://naoghuman.github.io/lib-i18n/apidocs
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[JDK 8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JRE 8]:http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[Lib-Logger]:https://github.com/Naoghuman/lib-logger
[Locale]:https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html
[log4j-api-2.10.0.jar]:https://logging.apache.org/log4j/2.0/log4j-web/dependencies.html
[log4j-core-2.10.0.jar]:https://logging.apache.org/log4j/2.0/log4j-web/dependencies.html
[Maven]:http://maven.apache.org/
[NetBeans]:https://netbeans.org/
[Overview]:https://github.com/Naoghuman/lib-i18n/releases
[Pull Request]:https://help.github.com/articles/using-pull-requests
[Release v0.6.1]:https://github.com/Naoghuman/lib-i18n/releases/tag/v0.6.1
[ResourceBundle]:https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html
[Scene Builder]:https://gluonhq.com/products/scene-builder/
[StringBinding]:https://docs.oracle.com/javase/8/javafx/api/javafx/beans/binding/StringBinding.html
[UML]:https://en.wikipedia.org/wiki/Unified_Modeling_Language
