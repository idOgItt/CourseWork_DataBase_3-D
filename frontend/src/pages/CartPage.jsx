import React from "react";
import { useCart } from "../context/CartContext";
import { useNavigate } from "react-router-dom";

const CartPage = () => {
    const { cart, removeFromCart } = useCart();
    const navigate = useNavigate();

    const handleCheckout = () => {
        navigate("/checkout");
    };

    return (
        <div>
            <h1>Корзина</h1>
            {cart.length === 0 ? (
                <p>Корзина пуста</p>
            ) : (
                <div>
                    <ul>
                        {cart.map((item) => (
                            <li key={item.id}>
                                {item.name} - {item.price} руб.
                                <button onClick={() => removeFromCart(item.id)}>Удалить</button>
                            </li>
                        ))}
                    </ul>
                    <button onClick={handleCheckout} style={{ marginTop: "20px" }}>
                        Оплатить
                    </button>
                </div>
            )}
        </div>
    );
};

export default CartPage;
