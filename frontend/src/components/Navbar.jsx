import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
    return (
        <nav style={{
            padding: "10px",
            backgroundColor: "#f5f5f5",
            marginBottom: "20px",
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
        }}>
            <ul style={{
                listStyle: "none",
                margin: 0,
                padding: 0,
                display: "flex",
                alignItems: "center",
                gap: "15px",
            }}>
                <li><Link to="/" style={{ textDecoration: "none", color: "#333" }}>Главная</Link></li>
                <li><Link to="/login" style={{ textDecoration: "none", color: "#333" }}>Вход</Link></li>
                <li><Link to="/register" style={{ textDecoration: "none", color: "#333" }}>Регистрация</Link></li>
                <li><Link to="/catalog" style={{ textDecoration: "none", color: "#333" }}>Каталог</Link></li>
                <li><Link to="/about" style={{ textDecoration: "none", color: "#333" }}>О проекте</Link></li>
            </ul>
        </nav>
    );
};

export default Navbar;
