package utils;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;



public class DataProviderListener extends Reports implements IAnnotationTransformer {
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class cls, Constructor cnst, Method method) {
		
		
		try {
			annotation.setDataProviderClass(ExcelDataProvider.class);
			annotation.setDataProvider("getExcelDataByKeys");
}catch (Exception e) {
	// TODO: handle exception
}
	}
}
