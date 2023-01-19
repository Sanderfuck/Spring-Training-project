In order to be able to authenticate in NIX GitLab Package Registry for PPP and get the artifacts needed for completing assignments do the following:
1. Create `${HOME}/.m2/settings.xml` file
2. Add the following to the newly created file:
```xml
<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd"
    xmlns="http://maven.apache.org/SETTINGS/1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <servers>
        <server>
            <id>gitlab-maven</id>
            <configuration>
                <httpHeaders>
                    <property>
                        <name>Deploy-Token</name>
                        <value>geG6hDzn_72QvoPWhGMs</value>
                    </property>
                </httpHeaders>
            </configuration>
        </server>
    </servers>
</settings>
```
3. Remove invalid artifacts from local repository `rm -rf ${HOME}/.m2/repository/com/nixsolutions/ppp/interfaces`

Now you can try to compile the project:

`mvn compile`

and you should see in tepminal output something like

```
...

[INFO] -----------------< com.nixsolutions.ppp:01_base_types >-----------------
[INFO] Building 01_base_types 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from gitlab-maven: https://gitlab.nixdev.co/api/v4/groups/446/-/packages/maven/com/nixsolutions/ppp/interfaces/0.7/interfaces-0.7.pom
Downloaded from gitlab-maven: https://gitlab.nixdev.co/api/v4/groups/446/-/packages/maven/com/nixsolutions/ppp/interfaces/0.7/interfaces-0.7.pom (2.5 kB at 2.7 kB/s)
Downloading from gitlab-maven: https://gitlab.nixdev.co/api/v4/groups/446/-/packages/maven/com/nixsolutions/ppp/interfaces/0.7/interfaces-0.7.jar
Downloaded from gitlab-maven: https://gitlab.nixdev.co/api/v4/groups/446/-/packages/maven/com/nixsolutions/ppp/interfaces/0.7/interfaces-0.7.jar (13 kB at 27 kB/s)

...

[INFO] BUILD SUCCESS
```

