import React, { useEffect, useState } from "react";
import axios from "../api/httpClient";
import "../styles/HomePage.css";

const HomePage = () => {
    const [popularModels, setPopularModels] = useState([]);

    useEffect(() => {
        const fetchPopularModels = async () => {
            try {
                const response = await axios.get("/models/popular");
                setPopularModels(response.data);
            } catch (err) {
                console.error("Ошибка загрузки популярных моделей:", err);
            }
        };

        fetchPopularModels();
    }, []);

    return (
        <div style={{ textAlign: "center", padding: "20px" }}>
            <h1>Добро пожаловать в магазин 3D моделей!</h1>
            <p>
                Мы предлагаем широкий выбор 3D моделей для печати. Наш каталог включает разнообразные товары,
                которые можно заказать и загрузить.
            </p>
            <div style={{ marginTop: "20px" }}>
                <h2>Популярные модели</h2>
                <div style={{ display: "flex", justifyContent: "center", flexWrap: "wrap" }}>
                    {popularModels.map((model) => (
                        <div
                            key={model.id}
                            style={{
                                border: "1px solid #ccc",
                                borderRadius: "8px",
                                padding: "10px",
                                margin: "10px",
                                width: "200px",
                            }}
                        >
                            <h3>{model.name}</h3>
                            <p>Цена: {model.price} руб.</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default HomePage;
