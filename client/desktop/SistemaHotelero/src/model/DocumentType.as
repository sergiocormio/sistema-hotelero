/**
 * This is a generated sub-class of _DocumentType.as and is intended for behavior
 * customization.  This class is only generated when there is no file already present
 * at its target location.  Thus custom behavior that you add here will survive regeneration
 * of the super-class. 
 *
 * NOTE: Do not manually modify the RemoteClass mapping unless
 * your server representation of this class has changed and you've 
 * updated your ActionScriptGeneration,RemoteClass annotation on the
 * corresponding entity 
 **/ 
 
package model
{

import com.adobe.fiber.core.model_internal;

[RemoteClass (alias="com.cdz.sh.model.DocumentType")]
public class DocumentType extends _Super_DocumentType
{
    /** 
     * DO NOT MODIFY THIS STATIC INITIALIZER - IT IS NECESSARY
     * FOR PROPERLY SETTING UP THE REMOTE CLASS ALIAS FOR THIS CLASS
     *
     **/
     
    /**
     * Calling this static function will initialize RemoteClass aliases
     * for this value object as well as all of the value objects corresponding
     * to entities associated to this value object's entity.  
     */     
    public static function _initRemoteClassAlias() : void
    {
        _Super_DocumentType.model_internal::initRemoteClassAliasSingle(model.DocumentType);
        _Super_DocumentType.model_internal::initRemoteClassAliasAllRelated();
    }
     
    model_internal static function initRemoteClassAliasSingleChild() : void
    {
        _Super_DocumentType.model_internal::initRemoteClassAliasSingle(model.DocumentType);
    }
    
    {
        _Super_DocumentType.model_internal::initRemoteClassAliasSingle(model.DocumentType);
    }
    /** 
     * END OF DO NOT MODIFY SECTION
     *
     **/    
}

}