using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.ViewModels.Sales
{
    public class EditVMcs
    {
        [DisplayName("Id: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Id { get; set; }

        [DisplayName("Car id: ")]
        [Required]
        [Range(1, 100000)]
        public int CarId { get; set; }

        [DisplayName("Car name: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string CarName { get; set; }

        [DisplayName("Customer id: ")]
        [Required]
        [Range(1, 100000)]
        public int CustomerId { get; set; }

        [DisplayName("Sale date: ")]
        [Required]
        [DataType(DataType.Date)]
        public DateTime SaleDate { get; set; }

        public int EmployeeId { get; set; }
    }
}