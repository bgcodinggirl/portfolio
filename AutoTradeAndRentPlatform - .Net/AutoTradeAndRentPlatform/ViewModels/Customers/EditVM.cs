using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace AutoTradeAndRentPlatform.ViewModels.Customers
{
    public class EditVM
    {
        public int Id { get; set; }
        public int EmployeeId { get; set; }

        [DisplayName("First Name: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string FirstName { get; set; }

        [DisplayName("Last Name: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string LastName { get; set; }

        [DisplayName("EGN: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string EGN { get; set; }

        [DisplayName("Phone: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Phone { get; set; }

        [DisplayName("Address: ")]
        [Required(ErrorMessage = "This field is Required!")]
        public string Address { get; set; }
    }
}