import React from "react";
import { BrowserRouter } from "react-router-dom";
import AppRoutes from "./routes/AppRoutes";
import Navbar from "./components/Navbar";
import { CartProvider } from "./context/CartContext";

function App() {
    return (
        <CartProvider>
            <BrowserRouter>
                <Navbar />
                <AppRoutes />
            </BrowserRouter>
        </CartProvider>
    );
}

export default App;
