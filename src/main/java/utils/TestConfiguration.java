package utils;


import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestConfiguration {
	
	String excelsheetname() default "";
	String excelfilename() default "";
	
}