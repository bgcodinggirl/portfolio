using AutoTradeAndRentPlatform.Entities;
using AutoTradeAndRentPlatform.Filters;
using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.Sales;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    [AuthenticationFilter]
    public class SalesController : Controller
    {
        public ActionResult Index()
        {
            SalesRepository repo = new SalesRepository();
            List<Sale> items = repo.GetAll();

            ViewData["sales"] = items;

            return View();
        }

        [HttpGet]
        public ActionResult Edit()
        {
            Employee loggedEmployee = (Employee)Session["loggedEmployee"];

            Sale item = new Sale();

            EditVMcs model = new EditVMcs();
            model.Id = item.Id;
            model.CarId = item.CarId;
            model.CarName = item.CarName;
            model.CustomerId = item.CustomerId;
            model.SaleDate = item.SaleDate;
            model.EmployeeId = loggedEmployee.Id;

            return View(model);
        }

        [HttpPost]
        public ActionResult Edit(EditVMcs model)
        {
            Employee loggedEmployee = (Employee)Session["loggedEmployee"];

            if (!ModelState.IsValid)
                return View(model);

            SalesRepository repo = new SalesRepository();

            Sale item = new Sale();
            item.Id = model.Id;
            item.CarId = model.CarId;
            item.CarName = model.CarName;
            item.CustomerId = model.CustomerId;
            item.SaleDate = model.SaleDate;
            item.EmployeeId = loggedEmployee.Id;

            repo.Insert(item);

            return RedirectToAction("Index", "Sales");
        }
    }
}