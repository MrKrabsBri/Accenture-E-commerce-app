import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";
import AddItemPage from "../pages/AddItem";
import UpdateItemPage from "../pages/UpdateItemPage";
import Cart from "../pages/Cart/Cart";
import DetailsPage from "../pages/DetailsPage";

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/additem" element={<AddItemPage />} />
        <Route path="/updateitem" element={<UpdateItemPage />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/details" element={<DetailsPage />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
