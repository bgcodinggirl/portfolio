using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Entities
{
    public class Sale
    {
        public string Id { get; set; }
        public int CarId { get; set; }
        public string CarName { get; set; }
        public int CustomerId { get; set; }
        public DateTime SaleDate { get; set; }
        public int EmployeeId { get; set; }
        public string EmployeeName { get; set; }
    }
}