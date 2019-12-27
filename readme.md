# NFC Scan boilerplate

This repository aims to provide a strong base for a NFC Reading and Writing application

- It consist of a `NFCScanActivity` that will enable a pending intent to listen for tags only when this activity is launched.

To enable or disable NFC scan simulator (only for testing your logic, this will disable the actual NFC tag scanning) use

```groovy
    // build.gradle in app
    buildConfigField("Boolean", "NFC_SIMULATOR_ENABLED", 'true') 
    // Set true or false to enable or disable simulator
```
