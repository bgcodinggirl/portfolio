using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.ViewModels.Rents
{
    public class EditVmR
    {
        public int Id { get; set; }

        [DisplayName("Car id: ")]
        [Required]
        [Range(1, 100000)]
        public int CarId { get; set; }

        [DisplayName("Customer id: ")]
        [Required]
        [Range(1, 100000)]
        public int CustomerId { get; set; }

        [DisplayName("Rent start date: ")]
        [Required]
        [DataType(DataType.Date)]
        public DateTime RentStartDate { get; set; }

        [DisplayName("Rent end date: ")]
        [Required]
        [DataType(DataType.Date)]
        public DateTime RentEndDate { get; set; }

        [DisplayName("Price in total: ")]
        [Required(ErrorMessage = "This field is Required!")]
        [Range(1, 9999999999.99)]
        public decimal PriceInTotal { get; set; }

        public int EmployeeId { get; set; }
    }
}