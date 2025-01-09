import React, { useState } from "react";
import axios from "../api/httpClient";

const LoginPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("/users/authenticate", { email, password });
            const token = response.data.token;
            localStorage.setItem("token", token);
            window.location.href = "/";
        } catch (err) {
            setError("Неверный логин или пароль");
        }
    };

    return (
        <div>
            <h1>Вход</h1>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Пароль:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {error && <p style={{ color: "red" }}>{error}</p>}
                <button type="submit">Войти</button>
            </form>
        </div>
    );
};

export default LoginPage;
