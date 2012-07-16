/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Customer.as.
 */

package model
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.util.FiberUtils;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.Event;
import flash.events.EventDispatcher;
import model.CustomerPK;
import mx.binding.utils.ChangeWatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;
import mx.validators.ValidationResult;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Customer extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("com.cdz.sh.model.Customer") == null)
            {
                flash.net.registerClassAlias("com.cdz.sh.model.Customer", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("com.cdz.sh.model.Customer", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        model.CustomerPK.initRemoteClassAliasSingleChild();
        model.DocumentType.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _CustomerEntityMetadata;
    model_internal var _changedObjects:mx.collections.ArrayCollection = new ArrayCollection();

    public function getChangedObjects() : Array
    {
        _changedObjects.addItemAt(this,0);
        return _changedObjects.source;
    }

    public function clearChangedObjects() : void
    {
        _changedObjects.removeAll();
    }

    /**
     * properties
     */
    private var _internal_dateOfBirth : Date;
    private var _internal_region : Object;
    private var _internal_lastName : String;
    private var _internal_email : String;
    private var _internal_profession : String;
    private var _internal_customerPK : model.CustomerPK;
    private var _internal_language : Object;
    private var _internal_firstName : String;
    private var _internal_lastLodgementDate : Date;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Customer()
    {
        _model = new _CustomerEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "dateOfBirth", model_internal::setterListenerDateOfBirth));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "region", model_internal::setterListenerRegion));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "lastName", model_internal::setterListenerLastName));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "email", model_internal::setterListenerEmail));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "profession", model_internal::setterListenerProfession));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "customerPK", model_internal::setterListenerCustomerPK));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "language", model_internal::setterListenerLanguage));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "firstName", model_internal::setterListenerFirstName));
        model_internal::_changeWatcherArray.push(mx.binding.utils.ChangeWatcher.watch(this, "lastLodgementDate", model_internal::setterListenerLastLodgementDate));

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get dateOfBirth() : Date
    {
        return _internal_dateOfBirth;
    }

    [Bindable(event="propertyChange")]
    public function get region() : Object
    {
        return _internal_region;
    }

    [Bindable(event="propertyChange")]
    public function get lastName() : String
    {
        return _internal_lastName;
    }

    [Bindable(event="propertyChange")]
    public function get email() : String
    {
        return _internal_email;
    }

    [Bindable(event="propertyChange")]
    public function get profession() : String
    {
        return _internal_profession;
    }

    [Bindable(event="propertyChange")]
    public function get customerPK() : model.CustomerPK
    {
        return _internal_customerPK;
    }

    [Bindable(event="propertyChange")]
    public function get language() : Object
    {
        return _internal_language;
    }

    [Bindable(event="propertyChange")]
    public function get firstName() : String
    {
        return _internal_firstName;
    }

    [Bindable(event="propertyChange")]
    public function get lastLodgementDate() : Date
    {
        return _internal_lastLodgementDate;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set dateOfBirth(value:Date) : void
    {
        var oldValue:Date = _internal_dateOfBirth;
        if (oldValue !== value)
        {
            _internal_dateOfBirth = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dateOfBirth", oldValue, _internal_dateOfBirth));
        }
    }

    public function set region(value:Object) : void
    {
        var oldValue:Object = _internal_region;
        if (oldValue !== value)
        {
            _internal_region = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "region", oldValue, _internal_region));
        }
    }

    public function set lastName(value:String) : void
    {
        var oldValue:String = _internal_lastName;
        if (oldValue !== value)
        {
            _internal_lastName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastName", oldValue, _internal_lastName));
        }
    }

    public function set email(value:String) : void
    {
        var oldValue:String = _internal_email;
        if (oldValue !== value)
        {
            _internal_email = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "email", oldValue, _internal_email));
        }
    }

    public function set profession(value:String) : void
    {
        var oldValue:String = _internal_profession;
        if (oldValue !== value)
        {
            _internal_profession = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "profession", oldValue, _internal_profession));
        }
    }

    public function set customerPK(value:model.CustomerPK) : void
    {
        var oldValue:model.CustomerPK = _internal_customerPK;
        if (oldValue !== value)
        {
            _internal_customerPK = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "customerPK", oldValue, _internal_customerPK));
        }
    }

    public function set language(value:Object) : void
    {
        var oldValue:Object = _internal_language;
        if (oldValue !== value)
        {
            _internal_language = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "language", oldValue, _internal_language));
        }
    }

    public function set firstName(value:String) : void
    {
        var oldValue:String = _internal_firstName;
        if (oldValue !== value)
        {
            _internal_firstName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "firstName", oldValue, _internal_firstName));
        }
    }

    public function set lastLodgementDate(value:Date) : void
    {
        var oldValue:Date = _internal_lastLodgementDate;
        if (oldValue !== value)
        {
            _internal_lastLodgementDate = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastLodgementDate", oldValue, _internal_lastLodgementDate));
        }
    }

    /**
     * Data/source property setter listeners
     *
     * Each data property whose value affects other properties or the validity of the entity
     * needs to invalidate all previously calculated artifacts. These include:
     *  - any derived properties or constraints that reference the given data property.
     *  - any availability guards (variant expressions) that reference the given data property.
     *  - any style validations, message tokens or guards that reference the given data property.
     *  - the validity of the property (and the containing entity) if the given data property has a length restriction.
     *  - the validity of the property (and the containing entity) if the given data property is required.
     */

    model_internal function setterListenerDateOfBirth(value:flash.events.Event):void
    {
        _model.invalidateDependentOnDateOfBirth();
    }

    model_internal function setterListenerRegion(value:flash.events.Event):void
    {
        _model.invalidateDependentOnRegion();
    }

    model_internal function setterListenerLastName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnLastName();
    }

    model_internal function setterListenerEmail(value:flash.events.Event):void
    {
        _model.invalidateDependentOnEmail();
    }

    model_internal function setterListenerProfession(value:flash.events.Event):void
    {
        _model.invalidateDependentOnProfession();
    }

    model_internal function setterListenerCustomerPK(value:flash.events.Event):void
    {
        _model.invalidateDependentOnCustomerPK();
    }

    model_internal function setterListenerLanguage(value:flash.events.Event):void
    {
        _model.invalidateDependentOnLanguage();
    }

    model_internal function setterListenerFirstName(value:flash.events.Event):void
    {
        _model.invalidateDependentOnFirstName();
    }

    model_internal function setterListenerLastLodgementDate(value:flash.events.Event):void
    {
        _model.invalidateDependentOnLastLodgementDate();
    }


    /**
     * valid related derived properties
     */
    model_internal var _isValid : Boolean;
    model_internal var _invalidConstraints:Array = new Array();
    model_internal var _validationFailureMessages:Array = new Array();

    /**
     * derived property calculators
     */

    /**
     * isValid calculator
     */
    model_internal function calculateIsValid():Boolean
    {
        var violatedConsts:Array = new Array();
        var validationFailureMessages:Array = new Array();

        var propertyValidity:Boolean = true;
        if (!_model.dateOfBirthIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_dateOfBirthValidationFailureMessages);
        }
        if (!_model.regionIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_regionValidationFailureMessages);
        }
        if (!_model.lastNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_lastNameValidationFailureMessages);
        }
        if (!_model.emailIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_emailValidationFailureMessages);
        }
        if (!_model.professionIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_professionValidationFailureMessages);
        }
        if (!_model.customerPKIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_customerPKValidationFailureMessages);
        }
        if (!_model.languageIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_languageValidationFailureMessages);
        }
        if (!_model.firstNameIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_firstNameValidationFailureMessages);
        }
        if (!_model.lastLodgementDateIsValid)
        {
            propertyValidity = false;
            com.adobe.fiber.util.FiberUtils.arrayAdd(validationFailureMessages, _model.model_internal::_lastLodgementDateValidationFailureMessages);
        }

        model_internal::_cacheInitialized_isValid = true;
        model_internal::invalidConstraints_der = violatedConsts;
        model_internal::validationFailureMessages_der = validationFailureMessages;
        return violatedConsts.length == 0 && propertyValidity;
    }

    /**
     * derived property setters
     */

    model_internal function set isValid_der(value:Boolean) : void
    {
        var oldValue:Boolean = model_internal::_isValid;
        if (oldValue !== value)
        {
            model_internal::_isValid = value;
            _model.model_internal::fireChangeEvent("isValid", oldValue, model_internal::_isValid);
        }
    }

    /**
     * derived property getters
     */

    [Transient]
    [Bindable(event="propertyChange")]
    public function get _model() : _CustomerEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _CustomerEntityMetadata) : void
    {
        var oldValue : _CustomerEntityMetadata = model_internal::_dminternal_model;
        if (oldValue !== value)
        {
            model_internal::_dminternal_model = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_model", oldValue, model_internal::_dminternal_model));
        }
    }

    /**
     * methods
     */


    /**
     *  services
     */
    private var _managingService:com.adobe.fiber.services.IFiberManagingService;

    public function set managingService(managingService:com.adobe.fiber.services.IFiberManagingService):void
    {
        _managingService = managingService;
    }

    model_internal function set invalidConstraints_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_invalidConstraints;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_invalidConstraints = value;
            _model.model_internal::fireChangeEvent("invalidConstraints", oldValue, model_internal::_invalidConstraints);
        }
    }

    model_internal function set validationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_validationFailureMessages;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_validationFailureMessages = value;
            _model.model_internal::fireChangeEvent("validationFailureMessages", oldValue, model_internal::_validationFailureMessages);
        }
    }

    model_internal var _doValidationCacheOfDateOfBirth : Array = null;
    model_internal var _doValidationLastValOfDateOfBirth : Date;

    model_internal function _doValidationForDateOfBirth(valueIn:Object):Array
    {
        var value : Date = valueIn as Date;

        if (model_internal::_doValidationCacheOfDateOfBirth != null && model_internal::_doValidationLastValOfDateOfBirth == value)
           return model_internal::_doValidationCacheOfDateOfBirth ;

        _model.model_internal::_dateOfBirthIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isDateOfBirthAvailable && _internal_dateOfBirth == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "dateOfBirth is required"));
        }

        model_internal::_doValidationCacheOfDateOfBirth = validationFailures;
        model_internal::_doValidationLastValOfDateOfBirth = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfRegion : Array = null;
    model_internal var _doValidationLastValOfRegion : Object;

    model_internal function _doValidationForRegion(valueIn:Object):Array
    {
        var value : Object = valueIn as Object;

        if (model_internal::_doValidationCacheOfRegion != null && model_internal::_doValidationLastValOfRegion == value)
           return model_internal::_doValidationCacheOfRegion ;

        _model.model_internal::_regionIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isRegionAvailable && _internal_region == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "region is required"));
        }

        model_internal::_doValidationCacheOfRegion = validationFailures;
        model_internal::_doValidationLastValOfRegion = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfLastName : Array = null;
    model_internal var _doValidationLastValOfLastName : String;

    model_internal function _doValidationForLastName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfLastName != null && model_internal::_doValidationLastValOfLastName == value)
           return model_internal::_doValidationCacheOfLastName ;

        _model.model_internal::_lastNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isLastNameAvailable && _internal_lastName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "lastName is required"));
        }

        model_internal::_doValidationCacheOfLastName = validationFailures;
        model_internal::_doValidationLastValOfLastName = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfEmail : Array = null;
    model_internal var _doValidationLastValOfEmail : String;

    model_internal function _doValidationForEmail(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfEmail != null && model_internal::_doValidationLastValOfEmail == value)
           return model_internal::_doValidationCacheOfEmail ;

        _model.model_internal::_emailIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isEmailAvailable && _internal_email == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "email is required"));
        }

        model_internal::_doValidationCacheOfEmail = validationFailures;
        model_internal::_doValidationLastValOfEmail = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfProfession : Array = null;
    model_internal var _doValidationLastValOfProfession : String;

    model_internal function _doValidationForProfession(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfProfession != null && model_internal::_doValidationLastValOfProfession == value)
           return model_internal::_doValidationCacheOfProfession ;

        _model.model_internal::_professionIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isProfessionAvailable && _internal_profession == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "profession is required"));
        }

        model_internal::_doValidationCacheOfProfession = validationFailures;
        model_internal::_doValidationLastValOfProfession = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfCustomerPK : Array = null;
    model_internal var _doValidationLastValOfCustomerPK : model.CustomerPK;

    model_internal function _doValidationForCustomerPK(valueIn:Object):Array
    {
        var value : model.CustomerPK = valueIn as model.CustomerPK;

        if (model_internal::_doValidationCacheOfCustomerPK != null && model_internal::_doValidationLastValOfCustomerPK == value)
           return model_internal::_doValidationCacheOfCustomerPK ;

        _model.model_internal::_customerPKIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isCustomerPKAvailable && _internal_customerPK == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "customerPK is required"));
        }

        model_internal::_doValidationCacheOfCustomerPK = validationFailures;
        model_internal::_doValidationLastValOfCustomerPK = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfLanguage : Array = null;
    model_internal var _doValidationLastValOfLanguage : Object;

    model_internal function _doValidationForLanguage(valueIn:Object):Array
    {
        var value : Object = valueIn as Object;

        if (model_internal::_doValidationCacheOfLanguage != null && model_internal::_doValidationLastValOfLanguage == value)
           return model_internal::_doValidationCacheOfLanguage ;

        _model.model_internal::_languageIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isLanguageAvailable && _internal_language == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "language is required"));
        }

        model_internal::_doValidationCacheOfLanguage = validationFailures;
        model_internal::_doValidationLastValOfLanguage = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfFirstName : Array = null;
    model_internal var _doValidationLastValOfFirstName : String;

    model_internal function _doValidationForFirstName(valueIn:Object):Array
    {
        var value : String = valueIn as String;

        if (model_internal::_doValidationCacheOfFirstName != null && model_internal::_doValidationLastValOfFirstName == value)
           return model_internal::_doValidationCacheOfFirstName ;

        _model.model_internal::_firstNameIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isFirstNameAvailable && _internal_firstName == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "firstName is required"));
        }

        model_internal::_doValidationCacheOfFirstName = validationFailures;
        model_internal::_doValidationLastValOfFirstName = value;

        return validationFailures;
    }
    
    model_internal var _doValidationCacheOfLastLodgementDate : Array = null;
    model_internal var _doValidationLastValOfLastLodgementDate : Date;

    model_internal function _doValidationForLastLodgementDate(valueIn:Object):Array
    {
        var value : Date = valueIn as Date;

        if (model_internal::_doValidationCacheOfLastLodgementDate != null && model_internal::_doValidationLastValOfLastLodgementDate == value)
           return model_internal::_doValidationCacheOfLastLodgementDate ;

        _model.model_internal::_lastLodgementDateIsValidCacheInitialized = true;
        var validationFailures:Array = new Array();
        var errorMessage:String;
        var failure:Boolean;

        var valRes:ValidationResult;
        if (_model.isLastLodgementDateAvailable && _internal_lastLodgementDate == null)
        {
            validationFailures.push(new ValidationResult(true, "", "", "lastLodgementDate is required"));
        }

        model_internal::_doValidationCacheOfLastLodgementDate = validationFailures;
        model_internal::_doValidationLastValOfLastLodgementDate = value;

        return validationFailures;
    }
    

}

}
