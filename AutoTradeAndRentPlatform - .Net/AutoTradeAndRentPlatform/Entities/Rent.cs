using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Entities
{
    public class Rent
    {
        public int Id { get; set; }
        public int CarId { get; set; }
        public int CustomerId { get; set; }
        public DateTime RentStartDate { get; set; }
        public DateTime RentEndDate { get; set; }
        public decimal PriceInTotal { get; set; }
        public int EmployeeId { get; set; }
        public string EmployeeName { get; set; }
    }
}