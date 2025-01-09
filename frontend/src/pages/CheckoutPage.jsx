import React from "react";
import { useCart } from "../context/CartContext";

const CheckoutPage = () => {
    const { cart } = useCart();

    const handleOrderSubmit = () => {
        alert("Заказ успешно оформлен!");
    };

    if (cart.length === 0) {
        return <p>Корзина пуста. Оформление заказа невозможно.</p>;
    }

    return (
        <div>
            <h1>Оформление заказа</h1>
            <ul>
                {cart.map((item) => (
                    <li key={item.id}>
                        {item.name} - {item.price} руб.
                    </li>
                ))}
            </ul>
            <p>Итого: {cart.reduce((sum, item) => sum + item.price, 0)} руб.</p>
            <button onClick={handleOrderSubmit}>Оформить заказ</button>
        </div>
    );
};

export default CheckoutPage;
