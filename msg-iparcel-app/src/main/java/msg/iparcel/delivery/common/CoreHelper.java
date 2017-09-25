package msg.iparcel.delivery.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CoreHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreHelper.class);
	
	public Object createModelStructure(Object emptyModel) throws Exception{
		List<Object> objects = new ArrayList<>();
		objects.add(emptyModel);
		while(objects.size()>0){
			Object loopObject = objects.get(0);
			objects.remove(0);
			// Field in Class
			Field[] fields = null;
			Method[] methods = null;
			if(loopObject.getClass().isAssignableFrom(String.class) ||
					loopObject.getClass().isAssignableFrom(Date.class) || 
					loopObject.getClass().isAssignableFrom(Boolean.class) || 
					loopObject.getClass().isAssignableFrom(boolean.class)){
				continue;
			} else {
				fields = loopObject.getClass().getDeclaredFields();
				methods = loopObject.getClass().getDeclaredMethods();
			}
			
			for(Field field : fields){
				//LOGGER.info("Field Name [" + field.getName() + "] & field type [" + field.getType() + "]");

				Class<?> clazzField = field.getType();
				if(List.class.isAssignableFrom(clazzField)){
					//Handle List
					List<Object> list = new ArrayList<>();
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					Class<?> member = (Class<?>) listType.getActualTypeArguments()[0];
					Object memberObject = member.newInstance();
					list.add(memberObject);
					Method setMethod = getMethodByName(Constant.SET+ field.getName(),methods);
					setMethod.invoke(loopObject,list);
					objects.add(memberObject);
				}else if(String.class.isAssignableFrom(clazzField) || Long.class.isAssignableFrom(clazzField)
						|| byte[].class.isAssignableFrom(clazzField) || Date.class.isAssignableFrom(clazzField)
						|| UUID.class.isAssignableFrom(clazzField) || Boolean.class.isAssignableFrom(clazzField)
						|| Double.class.isAssignableFrom(clazzField) || Integer.class.isAssignableFrom(clazzField)){
					continue;
				}else if(Object.class.isAssignableFrom(clazzField)){
					// handle object
					Method setMethod = getMethodByName(Constant.SET+ field.getName(),methods);
					Object fieldObject = clazzField.newInstance();
					setMethod.invoke(loopObject, fieldObject);
					objects.add(fieldObject);
					
					
				}
			}
			//Filed in Supper Class
			Class<?> supperClazz = loopObject.getClass().getSuperclass();
			while(supperClazz!=null){
				Field[] fieldSuppers = supperClazz.getDeclaredFields();
				Method[] methodSuppers = supperClazz.getDeclaredMethods();
				for(Field field : fieldSuppers){
					Class<?> clazzField = field.getType();
					if(List.class.isAssignableFrom(clazzField)){
						//Handle List
						List<Object> list = new ArrayList<>();
						ParameterizedType listType = (ParameterizedType) field.getGenericType();
						Class<?> member = (Class<?>) listType.getActualTypeArguments()[0];
						Object memberObject = member.newInstance();
						list.add(memberObject);
						Method setMethod = getMethodByName(Constant.SET+ field.getName(),methodSuppers);
						setMethod.invoke(loopObject,list);
						objects.add(memberObject);
					}/*else if(Date.class.isAssignableFrom(clazzField)){
						Method setMethod = getMethodByName(EmailConstant.SET+ field.getName(),methods);
						Date date = new Date();
						setMethod.invoke(loopObject, date);
					}*/else if(UUID.class.isAssignableFrom(clazzField) || Long.class.isAssignableFrom(clazzField)
							|| String.class.isAssignableFrom(clazzField)|| Date.class.isAssignableFrom(clazzField)){
						continue;
					}else if(Object.class.isAssignableFrom(clazzField)){
						// handle object
						Method setMethod = getMethodByName(Constant.SET+ field.getName(),methodSuppers);
						Object fieldObject = clazzField.newInstance();
						setMethod.invoke(loopObject, fieldObject);
						objects.add(fieldObject);
					}
				}
				supperClazz = supperClazz.getSuperclass();
			}
		}
		
		return emptyModel;
	}
	
	public Method getMethodByName(String name, Method[] methods){
		for(Method method : methods){
			if(method.getName().equalsIgnoreCase(name)){
				return method;
			}
		}
		return null;
	}

	public Date getTimeNow(){
		ZonedDateTime now = ZonedDateTime.now( ZoneOffset.systemDefault());
		Date dateNow = Date.from(now.toInstant());
		return dateNow;
	}
	
	public String getZonedDateTimeNow(){
		ZonedDateTime now = ZonedDateTime.now( ZoneOffset.systemDefault());
		return now.toString();
	}
	
	public String getTimeFolder(Date date){
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss");   

		String dateFormat = df.format(date);

		// Print what date is today!
		LOGGER.info("Date after modify: " + dateFormat);
		
		return dateFormat;
	}
	
    public void writeBytesToFileNio(byte[] bFile, String fileDest) {

    	File file = new File(fileDest);
        try {
            Path path = Paths.get(file.getPath());
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}