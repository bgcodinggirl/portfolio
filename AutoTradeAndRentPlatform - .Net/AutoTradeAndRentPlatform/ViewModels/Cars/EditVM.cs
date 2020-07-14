using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.ViewModels.Cars
{
    public class EditVM
    {
        public int Id { get; set; }

        [DisplayName("Make: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Make { get; set; }

        [DisplayName("Model: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Marka { get; set; }

        [Required(ErrorMessage = "This field is Required!")]
        [Range(1970, 2019)]
        public int Year { get; set; }

        [Required(ErrorMessage = "This field is Required!")]
        [Range(1, 9999999999.99)]
        public decimal Price { get; set; }

        [DisplayName("Color: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Color { get; set; }
    }
}