using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.Entities
{
    public class RentACar
    {
        public int Id { get; set; }
        public int CarId { get; set; }
        public string CarName { get; set; }
        public int CarYear { get; set; }
        public decimal PricePerDay { get; set; }
        public string Status { get; set; }
    }
}