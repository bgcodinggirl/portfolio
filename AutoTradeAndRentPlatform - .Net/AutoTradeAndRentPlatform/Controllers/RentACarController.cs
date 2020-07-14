using AutoTradeAndRentPlatform.Entities;
using AutoTradeAndRentPlatform.Filters;
using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.RentACar;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    [AuthenticationFilter]
    public class RentACarController : Controller
    {
        public ActionResult Index()
        {
            RentACarRepository repo = new RentACarRepository();
            List<RentACar> items = repo.GetAll();
            ViewData["rentacar"] = items;
            return View();
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            RentACarRepository repo = new RentACarRepository();
            RentACar item = id == null ? new RentACar() : repo.GetById(id.Value);

            EditVm model = new EditVm();
            model.Id = item.Id;
            model.CarId = item.CarId;
            model.PricePerDay = item.PricePerDay;
            model.Status = item.Status;

            return View(model);
        }

        [HttpPost]
        public ActionResult Edit(EditVm model)
        {
            if (!ModelState.IsValid)
                return View(model);

            RentACarRepository repo = new RentACarRepository();

            RentACar item = new RentACar();
            item.Id = model.Id;
            item.CarId = model.CarId;
            item.PricePerDay = model.PricePerDay;
            item.Status = model.Status;

            if (item.Id > 0)
                repo.Update(item);
            else
                repo.Insert(item);

            return RedirectToAction("Index", "RentACar");
        }

        public ActionResult Delete(int id)
        {
            RentACarRepository repo = new RentACarRepository();

            repo.Delete(id);

            return RedirectToAction("Index", "RentACar");
        }
    }
}