-libraryjars  rt.jar
-libraryjars  jce.jar
-libraryjars  jsse.jar


-obfuscationdictionary dictionaries/compact.txt
-classobfuscationdictionary dictionaries/shakespeare.txt


#-overloadaggressively 
#-defaultpackage '' 

#-allowaccessmodification 
#-dontoptimize 

-keepattributes *Annotation*


-keep public class * implements java.io.Serializable{ 
	public protected private *; 
}


-keep public class **.entity.* { 
    public protected private *; 
} 


-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}


-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}
