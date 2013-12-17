package dev.scroopid.crafexEngine.save;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that causes the SaveManager to ignore Saving fields/localVariables.
 * 
 * @author jameswomack
 * 
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.LOCAL_VARIABLE })
public @interface Ignore {

}
