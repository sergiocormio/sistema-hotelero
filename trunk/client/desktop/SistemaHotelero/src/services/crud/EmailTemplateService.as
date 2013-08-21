package services.crud
{
	import model.EmailTemplatePK;

	public class EmailTemplateService extends CRUDService
	{
		public function EmailTemplateService()
		{
			super("emailTemplate");
		}
		
		public function getRecordById(emailTplPk:EmailTemplatePK, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.getRecordById.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.getRecordById.addEventListener("fault", faultHandler);
			}
			remoteObject.getRecordById(emailTplPk);
		}
	}
}