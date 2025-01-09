import React, { useEffect, useState } from "react";
import axios from "../api/httpClient";

const ManageModels = () => {
    const [models, setModels] = useState([]);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchModels = async () => {
            try {
                const response = await axios.get("/models");
                setModels(response.data);
            } catch (err) {
                console.error("Ошибка загрузки моделей:", err);
                setError("Не удалось загрузить модели.");
            }
        };

        fetchModels();
    }, []);

    const deleteModel = async (id) => {
        try {
            await axios.delete(`/models/${id}`);
            setModels(models.filter((model) => model.id !== id));
        } catch (err) {
            console.error("Ошибка удаления модели:", err);
            setError("Не удалось удалить модель.");
        }
    };

    const addModel = async (newModel) => {
        try {
            const response = await axios.post("/models", newModel);
            setModels([...models, response.data]); // Обновляем список
        } catch (err) {
            console.error("Ошибка добавления модели:", err);
            setError("Не удалось добавить модель.");
        }
    };

    return (
        <div>
            <h1>Управление моделями</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <form
                onSubmit={(e) => {
                    e.preventDefault();
                    const newModel = {
                        name: "Новая модель",
                        description: "Описание",
                        price: 1000,
                    };
                    addModel(newModel);
                }}
            >
                <button type="submit">Добавить модель</button>
            </form>
            <table style={{ width: "100%", borderCollapse: "collapse" }}>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                {models.map((model) => (
                    <tr key={model.id}>
                        <td>{model.id}</td>
                        <td>{model.name}</td>
                        <td>{model.description}</td>
                        <td>{model.price} руб.</td>
                        <td>
                            <button onClick={() => alert(`Редактировать модель ${model.id}`)}>
                                Редактировать
                            </button>
                            <button onClick={() => deleteModel(model.id)}>Удалить</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ManageModels;
