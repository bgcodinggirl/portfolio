using AutoTradeAndRentPlatform.Entities;
using AutoTradeAndRentPlatform.Filters;
using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.Customers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    [AuthenticationFilter]
    public class CustomersController : Controller
    {
        public ActionResult Index()
        {
            Employee loggedEmployee = (Employee)Session["loggedEmployee"];

            CustomersRepository repo = new CustomersRepository();

            IndexVM model = new IndexVM();
            if (loggedEmployee.Id == 1)
            {
                model.Clients = repo.GetAll();
            }
            else
            {
                model.Clients = repo.GetAll(loggedEmployee.Id);
            }
            return View(model);
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            Employee loggedEmployee = (Employee)Session["loggedEmployee"];
            CustomersRepository repo = new CustomersRepository();
            Customer item = id == null ? new Customer() : repo.GetById(id.Value);

            EditVM model = new EditVM();
            model.Id = item.Id;
            model.EmployeeId = loggedEmployee.Id;
            model.FirstName = item.FirstName;
            model.LastName = item.LastName;
            model.EGN = item.EGN;
            model.Phone = item.Phone;
            model.Address = item.Address;

            return View(model);
        }

        [HttpPost]
        public ActionResult Edit(EditVM model)
        {
            Employee loggedEmployee = (Employee)Session["loggedEmployee"];

            if (!ModelState.IsValid)
                return View(model);

            CustomersRepository repo = new CustomersRepository();

            Customer item = new Customer();
            item.Id = model.Id;
            item.EmployeeId = loggedEmployee.Id;
            item.FirstName = model.FirstName;
            item.LastName = model.LastName;
            item.EGN = model.EGN;
            item.Phone = model.Phone;
            item.Address = model.Address;

            if (item.Id > 0)
                repo.Update(item);
            else
                repo.Insert(item);

            return RedirectToAction("Index", "Customers");
        }

        public ActionResult Delete(int id)
        {
            CustomersRepository repo = new CustomersRepository();

            repo.Delete(id);

            return RedirectToAction("Index", "Customers");
        }

    }
}