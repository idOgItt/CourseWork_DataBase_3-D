import React, { useEffect, useState } from "react";
import { useCart } from "../context/CartContext"; // Используем корзину
import axios from "../api/httpClient";

const CatalogPage = () => {
    const [models, setModels] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [error, setError] = useState("");

    const { addToCart } = useCart();

    useEffect(() => {
        const fetchModelsAndCategories = async () => {
            try {
                const modelsResponse = await axios.get("/models");
                const categoriesResponse = await axios.get("/categories");
                setModels(modelsResponse.data);
                setCategories(categoriesResponse.data);
            } catch (err) {
                console.error("Ошибка загрузки данных:", err);
                setError("Не удалось загрузить модели или категории.");
            }
        };

        fetchModelsAndCategories();
    }, []);

    const filteredModels = selectedCategory
        ? models.filter((model) => model.categoryId === parseInt(selectedCategory))
        : models;

    return (
        <div>
            <h1>Каталог 3D моделей</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <div>
                <label>Фильтр по категории:</label>
                <select
                    onChange={(e) => setSelectedCategory(e.target.value)}
                    value={selectedCategory}
                >
                    <option value="">Все категории</option>
                    {categories.map((category) => (
                        <option key={category.id} value={category.id}>
                            {category.name}
                        </option>
                    ))}
                </select>
            </div>
            <div style={{ display: "flex", flexWrap: "wrap" }}>
                {filteredModels.map((model) => (
                    <div key={model.id} style={{ margin: "10px", border: "1px solid #ccc", padding: "10px" }}>
                        <h2>{model.name}</h2>
                        <p>{model.description}</p>
                        <p>Цена: {model.price} руб.</p>
                        <button onClick={() => addToCart(model)}>Добавить в корзину</button> {/* Кнопка */}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CatalogPage;
