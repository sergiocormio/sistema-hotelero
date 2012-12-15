package services
{
	import model.Alternative;
	import model.Budget;
	import model.ExchangeRate;

	public class BudgetService extends RemoteObjectWrapperService
	{
		public function BudgetService()
		{
			super("budgetService");
		}
		
		public function populateBudget(alternatice:Alternative, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.populateBudget.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.populateBudget.addEventListener("fault", faultHandler);
			}
			remoteObject.populateBudget(alternatice);
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