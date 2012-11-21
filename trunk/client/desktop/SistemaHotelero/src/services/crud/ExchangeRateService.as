package services.crud
{
	import model.Budget;
	import model.ExchangeRate;
	
	import services.RemoteObjectWrapperService;

	public class ExchangeRateService extends CRUDService
	{
		public function ExchangeRateService()
		{
			super("exchangeRateService");
		}
		
		public function convertTo(budget:Budget, exchangeRate:ExchangeRate, convert_resultHandler:Function, faultHandler:Function):void
		{
			remoteObject.convertTo.addEventListener("result", convert_resultHandler);
			if(faultHandler!=null){
				remoteObject.convertTo.addEventListener("fault", faultHandler);
			}
			remoteObject.convertTo(budget, exchangeRate);
			
		}
	}
}