using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.ViewModels.RentACar
{
    public class EditVm
    {
        public int Id { get; set; }

        [DisplayName("Car id: ")]
        [Required]
        [Range(1, 100000)]
        public int CarId { get; set; }

        [DisplayName("Price per day: ")]
        [Required(ErrorMessage = "This field is Required!")]
        [Range(1, 9999999999.99)]
        public decimal PricePerDay { get; set; }

        [DisplayName("Status: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Status { get; set; }
    }
}