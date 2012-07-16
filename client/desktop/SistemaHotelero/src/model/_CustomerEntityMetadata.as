
/**
 * This is a generated class and is not intended for modification.  
 */
package model
{
import com.adobe.fiber.styles.IStyle;
import com.adobe.fiber.styles.Style;
import com.adobe.fiber.styles.StyleValidator;
import com.adobe.fiber.valueobjects.AbstractEntityMetadata;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import model.CustomerPK;
import mx.events.ValidationResultEvent;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IModelType;
import mx.events.PropertyChangeEvent;

use namespace model_internal;

[ExcludeClass]
internal class _CustomerEntityMetadata extends com.adobe.fiber.valueobjects.AbstractEntityMetadata
{
    private static var emptyArray:Array = new Array();

    model_internal static var allProperties:Array = new Array("dateOfBirth", "region", "lastName", "email", "profession", "customerPK", "language", "firstName", "lastLodgementDate");
    model_internal static var allAssociationProperties:Array = new Array();
    model_internal static var allRequiredProperties:Array = new Array("dateOfBirth", "region", "lastName", "email", "profession", "customerPK", "language", "firstName", "lastLodgementDate");
    model_internal static var allAlwaysAvailableProperties:Array = new Array("dateOfBirth", "region", "lastName", "email", "profession", "customerPK", "language", "firstName", "lastLodgementDate");
    model_internal static var guardedProperties:Array = new Array();
    model_internal static var dataProperties:Array = new Array("dateOfBirth", "region", "lastName", "email", "profession", "customerPK", "language", "firstName", "lastLodgementDate");
    model_internal static var sourceProperties:Array = emptyArray
    model_internal static var nonDerivedProperties:Array = new Array("dateOfBirth", "region", "lastName", "email", "profession", "customerPK", "language", "firstName", "lastLodgementDate");
    model_internal static var derivedProperties:Array = new Array();
    model_internal static var collectionProperties:Array = new Array();
    model_internal static var collectionBaseMap:Object;
    model_internal static var entityName:String = "Customer";
    model_internal static var dependentsOnMap:Object;
    model_internal static var dependedOnServices:Array = new Array();
    model_internal static var propertyTypeMap:Object;

    
    model_internal var _dateOfBirthIsValid:Boolean;
    model_internal var _dateOfBirthValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _dateOfBirthIsValidCacheInitialized:Boolean = false;
    model_internal var _dateOfBirthValidationFailureMessages:Array;
    
    model_internal var _regionIsValid:Boolean;
    model_internal var _regionValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _regionIsValidCacheInitialized:Boolean = false;
    model_internal var _regionValidationFailureMessages:Array;
    
    model_internal var _lastNameIsValid:Boolean;
    model_internal var _lastNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _lastNameIsValidCacheInitialized:Boolean = false;
    model_internal var _lastNameValidationFailureMessages:Array;
    
    model_internal var _emailIsValid:Boolean;
    model_internal var _emailValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _emailIsValidCacheInitialized:Boolean = false;
    model_internal var _emailValidationFailureMessages:Array;
    
    model_internal var _professionIsValid:Boolean;
    model_internal var _professionValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _professionIsValidCacheInitialized:Boolean = false;
    model_internal var _professionValidationFailureMessages:Array;
    
    model_internal var _customerPKIsValid:Boolean;
    model_internal var _customerPKValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _customerPKIsValidCacheInitialized:Boolean = false;
    model_internal var _customerPKValidationFailureMessages:Array;
    
    model_internal var _languageIsValid:Boolean;
    model_internal var _languageValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _languageIsValidCacheInitialized:Boolean = false;
    model_internal var _languageValidationFailureMessages:Array;
    
    model_internal var _firstNameIsValid:Boolean;
    model_internal var _firstNameValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _firstNameIsValidCacheInitialized:Boolean = false;
    model_internal var _firstNameValidationFailureMessages:Array;
    
    model_internal var _lastLodgementDateIsValid:Boolean;
    model_internal var _lastLodgementDateValidator:com.adobe.fiber.styles.StyleValidator;
    model_internal var _lastLodgementDateIsValidCacheInitialized:Boolean = false;
    model_internal var _lastLodgementDateValidationFailureMessages:Array;

    model_internal var _instance:_Super_Customer;
    model_internal static var _nullStyle:com.adobe.fiber.styles.Style = new com.adobe.fiber.styles.Style();

    public function _CustomerEntityMetadata(value : _Super_Customer)
    {
        // initialize property maps
        if (model_internal::dependentsOnMap == null)
        {
            // dependents map
            model_internal::dependentsOnMap = new Object();
            model_internal::dependentsOnMap["dateOfBirth"] = new Array();
            model_internal::dependentsOnMap["region"] = new Array();
            model_internal::dependentsOnMap["lastName"] = new Array();
            model_internal::dependentsOnMap["email"] = new Array();
            model_internal::dependentsOnMap["profession"] = new Array();
            model_internal::dependentsOnMap["customerPK"] = new Array();
            model_internal::dependentsOnMap["language"] = new Array();
            model_internal::dependentsOnMap["firstName"] = new Array();
            model_internal::dependentsOnMap["lastLodgementDate"] = new Array();

            // collection base map
            model_internal::collectionBaseMap = new Object();
        }

        // Property type Map
        model_internal::propertyTypeMap = new Object();
        model_internal::propertyTypeMap["dateOfBirth"] = "Date";
        model_internal::propertyTypeMap["region"] = "Object";
        model_internal::propertyTypeMap["lastName"] = "String";
        model_internal::propertyTypeMap["email"] = "String";
        model_internal::propertyTypeMap["profession"] = "String";
        model_internal::propertyTypeMap["customerPK"] = "model.CustomerPK";
        model_internal::propertyTypeMap["language"] = "Object";
        model_internal::propertyTypeMap["firstName"] = "String";
        model_internal::propertyTypeMap["lastLodgementDate"] = "Date";

        model_internal::_instance = value;
        model_internal::_dateOfBirthValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForDateOfBirth);
        model_internal::_dateOfBirthValidator.required = true;
        model_internal::_dateOfBirthValidator.requiredFieldError = "dateOfBirth is required";
        //model_internal::_dateOfBirthValidator.source = model_internal::_instance;
        //model_internal::_dateOfBirthValidator.property = "dateOfBirth";
        model_internal::_regionValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForRegion);
        model_internal::_regionValidator.required = true;
        model_internal::_regionValidator.requiredFieldError = "region is required";
        //model_internal::_regionValidator.source = model_internal::_instance;
        //model_internal::_regionValidator.property = "region";
        model_internal::_lastNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForLastName);
        model_internal::_lastNameValidator.required = true;
        model_internal::_lastNameValidator.requiredFieldError = "lastName is required";
        //model_internal::_lastNameValidator.source = model_internal::_instance;
        //model_internal::_lastNameValidator.property = "lastName";
        model_internal::_emailValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForEmail);
        model_internal::_emailValidator.required = true;
        model_internal::_emailValidator.requiredFieldError = "email is required";
        //model_internal::_emailValidator.source = model_internal::_instance;
        //model_internal::_emailValidator.property = "email";
        model_internal::_professionValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForProfession);
        model_internal::_professionValidator.required = true;
        model_internal::_professionValidator.requiredFieldError = "profession is required";
        //model_internal::_professionValidator.source = model_internal::_instance;
        //model_internal::_professionValidator.property = "profession";
        model_internal::_customerPKValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForCustomerPK);
        model_internal::_customerPKValidator.required = true;
        model_internal::_customerPKValidator.requiredFieldError = "customerPK is required";
        //model_internal::_customerPKValidator.source = model_internal::_instance;
        //model_internal::_customerPKValidator.property = "customerPK";
        model_internal::_languageValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForLanguage);
        model_internal::_languageValidator.required = true;
        model_internal::_languageValidator.requiredFieldError = "language is required";
        //model_internal::_languageValidator.source = model_internal::_instance;
        //model_internal::_languageValidator.property = "language";
        model_internal::_firstNameValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForFirstName);
        model_internal::_firstNameValidator.required = true;
        model_internal::_firstNameValidator.requiredFieldError = "firstName is required";
        //model_internal::_firstNameValidator.source = model_internal::_instance;
        //model_internal::_firstNameValidator.property = "firstName";
        model_internal::_lastLodgementDateValidator = new StyleValidator(model_internal::_instance.model_internal::_doValidationForLastLodgementDate);
        model_internal::_lastLodgementDateValidator.required = true;
        model_internal::_lastLodgementDateValidator.requiredFieldError = "lastLodgementDate is required";
        //model_internal::_lastLodgementDateValidator.source = model_internal::_instance;
        //model_internal::_lastLodgementDateValidator.property = "lastLodgementDate";
    }

    override public function getEntityName():String
    {
        return model_internal::entityName;
    }

    override public function getProperties():Array
    {
        return model_internal::allProperties;
    }

    override public function getAssociationProperties():Array
    {
        return model_internal::allAssociationProperties;
    }

    override public function getRequiredProperties():Array
    {
         return model_internal::allRequiredProperties;   
    }

    override public function getDataProperties():Array
    {
        return model_internal::dataProperties;
    }

    public function getSourceProperties():Array
    {
        return model_internal::sourceProperties;
    }

    public function getNonDerivedProperties():Array
    {
        return model_internal::nonDerivedProperties;
    }

    override public function getGuardedProperties():Array
    {
        return model_internal::guardedProperties;
    }

    override public function getUnguardedProperties():Array
    {
        return model_internal::allAlwaysAvailableProperties;
    }

    override public function getDependants(propertyName:String):Array
    {
       if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a data property of entity Customer");
            
       return model_internal::dependentsOnMap[propertyName] as Array;  
    }

    override public function getDependedOnServices():Array
    {
        return model_internal::dependedOnServices;
    }

    override public function getCollectionProperties():Array
    {
        return model_internal::collectionProperties;
    }

    override public function getCollectionBase(propertyName:String):String
    {
        if (model_internal::collectionProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a collection property of entity Customer");

        return model_internal::collectionBaseMap[propertyName];
    }
    
    override public function getPropertyType(propertyName:String):String
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
            throw new Error(propertyName + " is not a property of Customer");

        return model_internal::propertyTypeMap[propertyName];
    }

    override public function getAvailableProperties():com.adobe.fiber.valueobjects.IPropertyIterator
    {
        return new com.adobe.fiber.valueobjects.AvailablePropertyIterator(this);
    }

    override public function getValue(propertyName:String):*
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " does not exist for entity Customer");
        }

        return model_internal::_instance[propertyName];
    }

    override public function setValue(propertyName:String, value:*):void
    {
        if (model_internal::nonDerivedProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " is not a modifiable property of entity Customer");
        }

        model_internal::_instance[propertyName] = value;
    }

    override public function getMappedByProperty(associationProperty:String):String
    {
        switch(associationProperty)
        {
            default:
            {
                return null;
            }
        }
    }

    override public function getPropertyLength(propertyName:String):int
    {
        switch(propertyName)
        {
            default:
            {
                return 0;
            }
        }
    }

    override public function isAvailable(propertyName:String):Boolean
    {
        if (model_internal::allProperties.indexOf(propertyName) == -1)
        {
            throw new Error(propertyName + " does not exist for entity Customer");
        }

        if (model_internal::allAlwaysAvailableProperties.indexOf(propertyName) != -1)
        {
            return true;
        }

        switch(propertyName)
        {
            default:
            {
                return true;
            }
        }
    }

    override public function getIdentityMap():Object
    {
        var returnMap:Object = new Object();

        return returnMap;
    }

    [Bindable(event="propertyChange")]
    override public function get invalidConstraints():Array
    {
        if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
        {
            return model_internal::_instance.model_internal::_invalidConstraints;
        }
        else
        {
            // recalculate isValid
            model_internal::_instance.model_internal::_isValid = model_internal::_instance.model_internal::calculateIsValid();
            return model_internal::_instance.model_internal::_invalidConstraints;        
        }
    }

    [Bindable(event="propertyChange")]
    override public function get validationFailureMessages():Array
    {
        if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
        {
            return model_internal::_instance.model_internal::_validationFailureMessages;
        }
        else
        {
            // recalculate isValid
            model_internal::_instance.model_internal::_isValid = model_internal::_instance.model_internal::calculateIsValid();
            return model_internal::_instance.model_internal::_validationFailureMessages;
        }
    }

    override public function getDependantInvalidConstraints(propertyName:String):Array
    {
        var dependants:Array = getDependants(propertyName);
        if (dependants.length == 0)
        {
            return emptyArray;
        }

        var currentlyInvalid:Array = invalidConstraints;
        if (currentlyInvalid.length == 0)
        {
            return emptyArray;
        }

        var filterFunc:Function = function(element:*, index:int, arr:Array):Boolean
        {
            return dependants.indexOf(element) > -1;
        }

        return currentlyInvalid.filter(filterFunc);
    }

    /**
     * isValid
     */
    [Bindable(event="propertyChange")] 
    public function get isValid() : Boolean
    {
        if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
        {
            return model_internal::_instance.model_internal::_isValid;
        }
        else
        {
            // recalculate isValid
            model_internal::_instance.model_internal::_isValid = model_internal::_instance.model_internal::calculateIsValid();
            return model_internal::_instance.model_internal::_isValid;
        }
    }

    [Bindable(event="propertyChange")]
    public function get isDateOfBirthAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isRegionAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isLastNameAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isEmailAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isProfessionAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isCustomerPKAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isLanguageAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isFirstNameAvailable():Boolean
    {
        return true;
    }

    [Bindable(event="propertyChange")]
    public function get isLastLodgementDateAvailable():Boolean
    {
        return true;
    }


    /**
     * derived property recalculation
     */
    public function invalidateDependentOnDateOfBirth():void
    {
        if (model_internal::_dateOfBirthIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfDateOfBirth = null;
            model_internal::calculateDateOfBirthIsValid();
        }
    }
    public function invalidateDependentOnRegion():void
    {
        if (model_internal::_regionIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfRegion = null;
            model_internal::calculateRegionIsValid();
        }
    }
    public function invalidateDependentOnLastName():void
    {
        if (model_internal::_lastNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfLastName = null;
            model_internal::calculateLastNameIsValid();
        }
    }
    public function invalidateDependentOnEmail():void
    {
        if (model_internal::_emailIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfEmail = null;
            model_internal::calculateEmailIsValid();
        }
    }
    public function invalidateDependentOnProfession():void
    {
        if (model_internal::_professionIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfProfession = null;
            model_internal::calculateProfessionIsValid();
        }
    }
    public function invalidateDependentOnCustomerPK():void
    {
        if (model_internal::_customerPKIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfCustomerPK = null;
            model_internal::calculateCustomerPKIsValid();
        }
    }
    public function invalidateDependentOnLanguage():void
    {
        if (model_internal::_languageIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfLanguage = null;
            model_internal::calculateLanguageIsValid();
        }
    }
    public function invalidateDependentOnFirstName():void
    {
        if (model_internal::_firstNameIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfFirstName = null;
            model_internal::calculateFirstNameIsValid();
        }
    }
    public function invalidateDependentOnLastLodgementDate():void
    {
        if (model_internal::_lastLodgementDateIsValidCacheInitialized )
        {
            model_internal::_instance.model_internal::_doValidationCacheOfLastLodgementDate = null;
            model_internal::calculateLastLodgementDateIsValid();
        }
    }

    model_internal function fireChangeEvent(propertyName:String, oldValue:Object, newValue:Object):void
    {
        this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, propertyName, oldValue, newValue));
    }

    [Bindable(event="propertyChange")]   
    public function get dateOfBirthStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get dateOfBirthValidator() : StyleValidator
    {
        return model_internal::_dateOfBirthValidator;
    }

    model_internal function set _dateOfBirthIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_dateOfBirthIsValid;         
        if (oldValue !== value)
        {
            model_internal::_dateOfBirthIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dateOfBirthIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get dateOfBirthIsValid():Boolean
    {
        if (!model_internal::_dateOfBirthIsValidCacheInitialized)
        {
            model_internal::calculateDateOfBirthIsValid();
        }

        return model_internal::_dateOfBirthIsValid;
    }

    model_internal function calculateDateOfBirthIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_dateOfBirthValidator.validate(model_internal::_instance.dateOfBirth)
        model_internal::_dateOfBirthIsValid_der = (valRes.results == null);
        model_internal::_dateOfBirthIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::dateOfBirthValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::dateOfBirthValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get dateOfBirthValidationFailureMessages():Array
    {
        if (model_internal::_dateOfBirthValidationFailureMessages == null)
            model_internal::calculateDateOfBirthIsValid();

        return _dateOfBirthValidationFailureMessages;
    }

    model_internal function set dateOfBirthValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_dateOfBirthValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_dateOfBirthValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dateOfBirthValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get regionStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get regionValidator() : StyleValidator
    {
        return model_internal::_regionValidator;
    }

    model_internal function set _regionIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_regionIsValid;         
        if (oldValue !== value)
        {
            model_internal::_regionIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "regionIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get regionIsValid():Boolean
    {
        if (!model_internal::_regionIsValidCacheInitialized)
        {
            model_internal::calculateRegionIsValid();
        }

        return model_internal::_regionIsValid;
    }

    model_internal function calculateRegionIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_regionValidator.validate(model_internal::_instance.region)
        model_internal::_regionIsValid_der = (valRes.results == null);
        model_internal::_regionIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::regionValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::regionValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get regionValidationFailureMessages():Array
    {
        if (model_internal::_regionValidationFailureMessages == null)
            model_internal::calculateRegionIsValid();

        return _regionValidationFailureMessages;
    }

    model_internal function set regionValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_regionValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_regionValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "regionValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get lastNameStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get lastNameValidator() : StyleValidator
    {
        return model_internal::_lastNameValidator;
    }

    model_internal function set _lastNameIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_lastNameIsValid;         
        if (oldValue !== value)
        {
            model_internal::_lastNameIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastNameIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get lastNameIsValid():Boolean
    {
        if (!model_internal::_lastNameIsValidCacheInitialized)
        {
            model_internal::calculateLastNameIsValid();
        }

        return model_internal::_lastNameIsValid;
    }

    model_internal function calculateLastNameIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_lastNameValidator.validate(model_internal::_instance.lastName)
        model_internal::_lastNameIsValid_der = (valRes.results == null);
        model_internal::_lastNameIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::lastNameValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::lastNameValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get lastNameValidationFailureMessages():Array
    {
        if (model_internal::_lastNameValidationFailureMessages == null)
            model_internal::calculateLastNameIsValid();

        return _lastNameValidationFailureMessages;
    }

    model_internal function set lastNameValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_lastNameValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_lastNameValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastNameValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get emailStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get emailValidator() : StyleValidator
    {
        return model_internal::_emailValidator;
    }

    model_internal function set _emailIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_emailIsValid;         
        if (oldValue !== value)
        {
            model_internal::_emailIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "emailIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get emailIsValid():Boolean
    {
        if (!model_internal::_emailIsValidCacheInitialized)
        {
            model_internal::calculateEmailIsValid();
        }

        return model_internal::_emailIsValid;
    }

    model_internal function calculateEmailIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_emailValidator.validate(model_internal::_instance.email)
        model_internal::_emailIsValid_der = (valRes.results == null);
        model_internal::_emailIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::emailValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::emailValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get emailValidationFailureMessages():Array
    {
        if (model_internal::_emailValidationFailureMessages == null)
            model_internal::calculateEmailIsValid();

        return _emailValidationFailureMessages;
    }

    model_internal function set emailValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_emailValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_emailValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "emailValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get professionStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get professionValidator() : StyleValidator
    {
        return model_internal::_professionValidator;
    }

    model_internal function set _professionIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_professionIsValid;         
        if (oldValue !== value)
        {
            model_internal::_professionIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "professionIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get professionIsValid():Boolean
    {
        if (!model_internal::_professionIsValidCacheInitialized)
        {
            model_internal::calculateProfessionIsValid();
        }

        return model_internal::_professionIsValid;
    }

    model_internal function calculateProfessionIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_professionValidator.validate(model_internal::_instance.profession)
        model_internal::_professionIsValid_der = (valRes.results == null);
        model_internal::_professionIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::professionValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::professionValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get professionValidationFailureMessages():Array
    {
        if (model_internal::_professionValidationFailureMessages == null)
            model_internal::calculateProfessionIsValid();

        return _professionValidationFailureMessages;
    }

    model_internal function set professionValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_professionValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_professionValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "professionValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get customerPKStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get customerPKValidator() : StyleValidator
    {
        return model_internal::_customerPKValidator;
    }

    model_internal function set _customerPKIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_customerPKIsValid;         
        if (oldValue !== value)
        {
            model_internal::_customerPKIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "customerPKIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get customerPKIsValid():Boolean
    {
        if (!model_internal::_customerPKIsValidCacheInitialized)
        {
            model_internal::calculateCustomerPKIsValid();
        }

        return model_internal::_customerPKIsValid;
    }

    model_internal function calculateCustomerPKIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_customerPKValidator.validate(model_internal::_instance.customerPK)
        model_internal::_customerPKIsValid_der = (valRes.results == null);
        model_internal::_customerPKIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::customerPKValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::customerPKValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get customerPKValidationFailureMessages():Array
    {
        if (model_internal::_customerPKValidationFailureMessages == null)
            model_internal::calculateCustomerPKIsValid();

        return _customerPKValidationFailureMessages;
    }

    model_internal function set customerPKValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_customerPKValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_customerPKValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "customerPKValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get languageStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get languageValidator() : StyleValidator
    {
        return model_internal::_languageValidator;
    }

    model_internal function set _languageIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_languageIsValid;         
        if (oldValue !== value)
        {
            model_internal::_languageIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "languageIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get languageIsValid():Boolean
    {
        if (!model_internal::_languageIsValidCacheInitialized)
        {
            model_internal::calculateLanguageIsValid();
        }

        return model_internal::_languageIsValid;
    }

    model_internal function calculateLanguageIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_languageValidator.validate(model_internal::_instance.language)
        model_internal::_languageIsValid_der = (valRes.results == null);
        model_internal::_languageIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::languageValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::languageValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get languageValidationFailureMessages():Array
    {
        if (model_internal::_languageValidationFailureMessages == null)
            model_internal::calculateLanguageIsValid();

        return _languageValidationFailureMessages;
    }

    model_internal function set languageValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_languageValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_languageValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "languageValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get firstNameStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get firstNameValidator() : StyleValidator
    {
        return model_internal::_firstNameValidator;
    }

    model_internal function set _firstNameIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_firstNameIsValid;         
        if (oldValue !== value)
        {
            model_internal::_firstNameIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "firstNameIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get firstNameIsValid():Boolean
    {
        if (!model_internal::_firstNameIsValidCacheInitialized)
        {
            model_internal::calculateFirstNameIsValid();
        }

        return model_internal::_firstNameIsValid;
    }

    model_internal function calculateFirstNameIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_firstNameValidator.validate(model_internal::_instance.firstName)
        model_internal::_firstNameIsValid_der = (valRes.results == null);
        model_internal::_firstNameIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::firstNameValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::firstNameValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get firstNameValidationFailureMessages():Array
    {
        if (model_internal::_firstNameValidationFailureMessages == null)
            model_internal::calculateFirstNameIsValid();

        return _firstNameValidationFailureMessages;
    }

    model_internal function set firstNameValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_firstNameValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_firstNameValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "firstNameValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }

    [Bindable(event="propertyChange")]   
    public function get lastLodgementDateStyle():com.adobe.fiber.styles.Style
    {
        return model_internal::_nullStyle;
    }

    public function get lastLodgementDateValidator() : StyleValidator
    {
        return model_internal::_lastLodgementDateValidator;
    }

    model_internal function set _lastLodgementDateIsValid_der(value:Boolean):void 
    {
        var oldValue:Boolean = model_internal::_lastLodgementDateIsValid;         
        if (oldValue !== value)
        {
            model_internal::_lastLodgementDateIsValid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastLodgementDateIsValid", oldValue, value));
        }                             
    }

    [Bindable(event="propertyChange")]
    public function get lastLodgementDateIsValid():Boolean
    {
        if (!model_internal::_lastLodgementDateIsValidCacheInitialized)
        {
            model_internal::calculateLastLodgementDateIsValid();
        }

        return model_internal::_lastLodgementDateIsValid;
    }

    model_internal function calculateLastLodgementDateIsValid():void
    {
        var valRes:ValidationResultEvent = model_internal::_lastLodgementDateValidator.validate(model_internal::_instance.lastLodgementDate)
        model_internal::_lastLodgementDateIsValid_der = (valRes.results == null);
        model_internal::_lastLodgementDateIsValidCacheInitialized = true;
        if (valRes.results == null)
             model_internal::lastLodgementDateValidationFailureMessages_der = emptyArray;
        else
        {
            var _valFailures:Array = new Array();
            for (var a:int = 0 ; a<valRes.results.length ; a++)
            {
                _valFailures.push(valRes.results[a].errorMessage);
            }
            model_internal::lastLodgementDateValidationFailureMessages_der = _valFailures;
        }
    }

    [Bindable(event="propertyChange")]
    public function get lastLodgementDateValidationFailureMessages():Array
    {
        if (model_internal::_lastLodgementDateValidationFailureMessages == null)
            model_internal::calculateLastLodgementDateIsValid();

        return _lastLodgementDateValidationFailureMessages;
    }

    model_internal function set lastLodgementDateValidationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_lastLodgementDateValidationFailureMessages;

        var needUpdate : Boolean = false;
        if (oldValue == null)
            needUpdate = true;
    
        // avoid firing the event when old and new value are different empty arrays
        if (!needUpdate && (oldValue !== value && (oldValue.length > 0 || value.length > 0)))
        {
            if (oldValue.length == value.length)
            {
                for (var a:int=0; a < oldValue.length; a++)
                {
                    if (oldValue[a] !== value[a])
                    {
                        needUpdate = true;
                        break;
                    }
                }
            }
            else
            {
                needUpdate = true;
            }
        }

        if (needUpdate)
        {
            model_internal::_lastLodgementDateValidationFailureMessages = value;   
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastLodgementDateValidationFailureMessages", oldValue, value));
            // Only execute calculateIsValid if it has been called before, to update the validationFailureMessages for
            // the entire entity.
            if (model_internal::_instance.model_internal::_cacheInitialized_isValid)
            {
                model_internal::_instance.model_internal::isValid_der = model_internal::_instance.model_internal::calculateIsValid();
            }
        }
    }


     /**
     * 
     * @inheritDoc 
     */ 
     override public function getStyle(propertyName:String):com.adobe.fiber.styles.IStyle
     {
         switch(propertyName)
         {
            default:
            {
                return null;
            }
         }
     }
     
     /**
     * 
     * @inheritDoc 
     *  
     */  
     override public function getPropertyValidationFailureMessages(propertyName:String):Array
     {
         switch(propertyName)
         {
            case("dateOfBirth"):
            {
                return dateOfBirthValidationFailureMessages;
            }
            case("region"):
            {
                return regionValidationFailureMessages;
            }
            case("lastName"):
            {
                return lastNameValidationFailureMessages;
            }
            case("email"):
            {
                return emailValidationFailureMessages;
            }
            case("profession"):
            {
                return professionValidationFailureMessages;
            }
            case("customerPK"):
            {
                return customerPKValidationFailureMessages;
            }
            case("language"):
            {
                return languageValidationFailureMessages;
            }
            case("firstName"):
            {
                return firstNameValidationFailureMessages;
            }
            case("lastLodgementDate"):
            {
                return lastLodgementDateValidationFailureMessages;
            }
            default:
            {
                return emptyArray;
            }
         }
     }

}

}
