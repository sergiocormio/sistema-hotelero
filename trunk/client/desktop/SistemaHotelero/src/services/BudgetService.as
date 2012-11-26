package services
{
	import model.Budget;
	import model.ExchangeRate;

	public class BudgetService extends RemoteObjectWrapperService
	{
		public function BudgetService()
		{
			super("budgetService");
		}
		
		public function exportData(budget:Budget, selectedLocale:String, selectedExchangeRate:ExchangeRate, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.exportData.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.exportData.addEventListener("fault", faultHandler);
			}
			remoteObject.exportData(budget, selectedLocale, selectedExchangeRate);
		}
	}
}