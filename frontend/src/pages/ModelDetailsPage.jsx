import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api/httpClient";

const ModelDetailsPage = () => {
    const { id } = useParams();
    const [model, setModel] = useState(null);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchModelDetails = async () => {
            return axios.get(`/models/${id}`);
        };

        fetchModelDetails()
            .then((response) => {
                setModel(response.data);
            })
            .catch((err) => {
                console.error("Ошибка загрузки деталей модели:", err);
                setError("Не удалось загрузить детали модели.");
            });
    }, [id]);

    if (error) {
        return <p style={{ color: "red" }}>{error}</p>;
    }

    if (!model) {
        return <p>Загрузка деталей модели...</p>;
    }

    return (
        <div>
            <h1>{model.name}</h1>
            <p>{model.description}</p>
            <p>Цена: {model.price ? `${model.price} руб.` : "Не указана"}</p>
            <p>Рейтинг: {model.rating || "Нет оценки"}</p>
            <p>Доступное количество: {model.quantityAvailable || "Нет информации"}</p>
        </div>
    );
};

export default ModelDetailsPage;
