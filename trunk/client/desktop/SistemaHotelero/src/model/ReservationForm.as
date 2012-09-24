package model
{
	[RemoteClass (alias="com.cdz.sh.model.ReservationForm")]
	public class ReservationForm
	{
		/*
		@Id
		@GeneratedValue
		private Long id;
		
		@ManyToOne
		@JoinColumns(value = {@JoinColumn(name = "CUSTOMER_DOC_TYPE"), @JoinColumn(name = "CUSTOMER_ID_NUMBER")} )
		private Customer customer;
		
		private StateReservationForm state;
		
		private Date creationDate;
		
		private Date dateFrom;
		
		private Date dateTo;
		
		private int adultsQuantity;
		
		private int childrenQuantity;
		
		private Double pricePerDay;
		
		private Double monetaryReserve;
		
		@ManyToOne
		@JoinColumn(name="Bank_ID")
		private Bank bank;
		
		private String bankDocumentNumber;
		*/
		public function ReservationForm()
		{
		}
	}
}