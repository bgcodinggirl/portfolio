using AutoTradeAndRentPlatform.Entities;
using AutoTradeAndRentPlatform.Filters;
using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.Rents;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    [AuthenticationFilter]
    public class RentsController : Controller
    {
        // GET: Rents
        public ActionResult Index()
        {
            RentsRepository repo = new RentsRepository();
            List<Rent> items = repo.GetAll();

            ViewData["rents"] = items;

            return View();
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            RentsRepository repo = new RentsRepository();
            Rent item = id == null ? new Rent() : repo.GetById(id.Value);

            EditVmR model = new EditVmR();
            model.Id = item.Id;
            model.CarId = item.CarId;
            model.CustomerId = item.CustomerId;
            model.RentStartDate = item.RentStartDate;
            model.RentEndDate = item.RentEndDate;
            model.PriceInTotal = item.PriceInTotal;
            model.EmployeeId = item.EmployeeId;

            return View(model);
        }

        [HttpPost]
        public ActionResult Edit(EditVmR model)
        {
            Employee loggedEmployee = (Employee)Session["loggedEmployee"];
            if (!ModelState.IsValid)
                return View(model);

            RentsRepository repo = new RentsRepository();

            Rent item = new Rent();
            item.Id = model.Id;
            item.CarId = model.CarId;
            item.CustomerId = model.CustomerId;
            item.RentStartDate = model.RentStartDate;
            item.RentEndDate = model.RentEndDate;
            item.PriceInTotal = model.PriceInTotal;
            item.EmployeeId = loggedEmployee.Id;

            if (item.Id > 0)
                repo.Update(item);
            else
                repo.Insert(item);

            return RedirectToAction("Index", "Rents");
        }

        public ActionResult Delete(int id)
        {
            RentsRepository repo = new RentsRepository();

            repo.Delete(id);

            return RedirectToAction("Index", "Rents");
        }
        
    }
}