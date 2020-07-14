using AutoTradeAndRentPlatform.Entities;
using AutoTradeAndRentPlatform.Filters;
using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.Employees;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    [AuthenticationFilter]
    public class EmployeesController : Controller
    {
        public ActionResult Index()
        {
            EmployeesRepository repo = new EmployeesRepository();
            List<Employee> items = repo.GetAll();

            ViewData["items"] = items;

            return View();
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            EmployeesRepository repo = new EmployeesRepository();
            Employee item = id == null ? new Employee() : repo.GetById(id.Value);

            EditVM model = new EditVM();
            model.Id = item.Id;
            model.Username = item.Username;
            model.Password = item.Password;
            model.FirstName = item.FirstName;
            model.LastName = item.LastName;
            model.Phone = item.Phone;
            model.Email = item.Email;

            return View(model);
        }

        [HttpPost]
        public ActionResult Edit(EditVM model)
        {
            if (!ModelState.IsValid)
                return View(model);

            EmployeesRepository repo = new EmployeesRepository();

            Employee item = new Employee();
            item.Id = model.Id;
            item.Username = model.Username;
            item.Password = model.Password;
            item.FirstName = model.FirstName;
            item.LastName = model.LastName;
            item.Phone = model.Phone;
            item.Email = model.Email;

            if (item.Id > 0)
                repo.Update(item);
            else
                repo.Insert(item);

            return RedirectToAction("Index", "Employees");
        }

        public ActionResult Delete(int id)
        {
            EmployeesRepository repo = new EmployeesRepository();

            repo.Delete(id);

            return RedirectToAction("Index", "Employees");
        }
    }

}