import React, { useEffect, useState } from "react";
import axios from "../api/httpClient";

const LogsPage = () => {
    const [logs, setLogs] = useState([]);
    const [error, setError] = useState("");
    const [filter, setFilter] = useState("");

    useEffect(() => {
        const fetchLogs = async () => {
            try {
                const response = await axios.get("/logs");
                setLogs(response.data);
            } catch (err) {
                console.error("Ошибка загрузки логов:", err);
                setError("Не удалось загрузить логи.");
            }
        };

        fetchLogs();
    }, []);

    const filteredLogs = logs.filter((log) =>
        log.action.toLowerCase().includes(filter.toLowerCase())
    );

    return (
        <div>
            <h1>Логи</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <input
                type="text"
                placeholder="Фильтр по действию"
                value={filter}
                onChange={(e) => setFilter(e.target.value)}
            />
            <ul>
                {filteredLogs.map((log) => (
                    <li key={log.id}>
                        [{log.timestamp}] {log.user} - {log.action}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default LogsPage;
