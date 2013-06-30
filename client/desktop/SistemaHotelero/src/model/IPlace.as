package model
{
	/**
	 * Represents an abstract place. It could be either a country or a region
	 * 
	 */
	public interface IPlace
	{
		/**
		 * Name to be displayed in AdvertisementForm MultiList
		 */
		function get qualifiedName():String;
	}
}