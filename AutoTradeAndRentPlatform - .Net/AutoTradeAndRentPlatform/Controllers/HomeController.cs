using AutoTradeAndRentPlatform.Repositories;
using AutoTradeAndRentPlatform.ViewModels.Home;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace AutoTradeAndRentPlatform.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }
        [HttpGet]
        public ActionResult Login()
        {
            LoginVM model = new LoginVM();
            return View(model);
        }
        [HttpPost]
        public ActionResult Login(LoginVM model)
        {
            if (!ModelState.IsValid)
                return View(model);

            EmployeesRepository repo = new EmployeesRepository();
            Session["loggedEmployee"] = repo.GetByUsernameAndPassword(model.Username, model.Password);

            if (Session["loggedEmployee"] == null)
                ModelState.AddModelError("AuthenticationFailed", "Authentication failed!");

            if (!ModelState.IsValid)
                return View(model);

            return RedirectToAction("Index", "Home");
        }

        public ActionResult Logout()
        {
            Session["loggedEmployee"] = null;

            return RedirectToAction("Login", "Home");
        }
    }
}