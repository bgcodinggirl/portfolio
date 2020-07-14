using AutoTradeAndRentPlatform.Entities;
using AutoTradeAndRentPlatform.Filters;
using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.Cars;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    public class CarsController : Controller
    {
        [AuthenticationFilter]
        public ActionResult Index()
        {
            CarsRepository repo = new CarsRepository();
            List<Car> items = repo.GetAll();
            ViewData["cars"] = items;
            return View();
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            CarsRepository repo = new CarsRepository();
            Car item = id == null ? new Car() : repo.GetById(id.Value);

            EditVM model = new EditVM();
            model.Id = item.Id;
            model.Make = item.Make;
            model.Marka = item.Model;
            model.Year = item.Year;
            model.Price = item.Price;
            model.Color = item.Color;

            return View(model);
        }

        [HttpPost]
        public ActionResult Edit(EditVM model)
        {
            if (!ModelState.IsValid)
                return View(model);

            CarsRepository repo = new CarsRepository();

            Car item = new Car();
            item.Id = model.Id;
            item.Make = model.Make;
            item.Model = model.Marka;
            item.Year = model.Year;
            item.Price = model.Price;
            item.Color = model.Color;

            if (item.Id > 0)
                repo.Update(item);
            else
                repo.Insert(item);

            return RedirectToAction("Index", "Cars");
        }

        public ActionResult Delete(int id)
        {
            CarsRepository repo = new CarsRepository();

            repo.Delete(id);

            return RedirectToAction("Index", "Cars");
        }
    }
}