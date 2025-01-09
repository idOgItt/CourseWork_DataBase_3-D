import React from "react";
import { Routes, Route } from "react-router-dom";
import HomePage from "../pages/HomePage";
import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import CatalogPage from "../pages/CatalogPage";
import ModelDetailsPage from "../pages/ModelDetailsPage";
import AdminPanel from "../pages/AdminPanel";
import ManageModels from "../pages/ManageModels";
import ManageUsers from "../pages/ManageUsers";
import ManageCategories from "../pages/ManageCategories";
import ProtectedRoute from "../components/ProtectedRoute";
import CartPage from "../pages/CartPage";
import CheckoutPage from "../pages/CheckoutPage";
import ManageRoles from "../pages/ManageRoles";
import ManageUserRoles from "../pages/ManageUserRoles";
import LogsPage from "../pages/LogsPage";

const AppRoutes = () => (
    <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/catalog" element={<CatalogPage />} />
        <Route path="/catalog/:id" element={<ModelDetailsPage />} />
        <Route path="/admin" element={<ProtectedRoute><AdminPanel /></ProtectedRoute>} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/checkout" element={<CheckoutPage />} />
        <Route path="/admin/roles" element={<ManageRoles />} />
        <Route path="/admin/user-roles" element={<ManageUserRoles />} />
        <Route path="/admin/logs" element={<LogsPage />} />
        <Route path="/admin/models" element={<ProtectedRoute><ManageModels /></ProtectedRoute>} />
        <Route path="/admin/users" element={<ProtectedRoute><ManageUsers /></ProtectedRoute>} />
        <Route path="/admin/categories" element={<ProtectedRoute><ManageCategories /></ProtectedRoute>} />
    </Routes>
);

export default AppRoutes;
