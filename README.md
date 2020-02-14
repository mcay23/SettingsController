# SettingsController

Simple settings file controller in Java for easy storage/retrieval.
Sorted by insertion order.

write() must be called to write changes in file

Usage Example:

```
File file = new File("settings.ini");
SettingsController settings = new SettingsController(file);
settings.add("name", "john");
settings.add("age", "20");
settings.add("occupation", "student");

settings.write()

System.out.println(settings.get("name"));
System.out.println(settings.get("age"));
System.out.println(settings.get("occupation"));
```
