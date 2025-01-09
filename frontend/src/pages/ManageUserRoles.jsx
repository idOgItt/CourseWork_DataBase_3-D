import React, { useEffect, useState } from "react";
import axios from "../api/httpClient";

const ManageUserRoles = () => {
    const [users, setUsers] = useState([]);
    const [roles, setRoles] = useState([]);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchUsersAndRoles = async () => {
            try {
                const usersResponse = await axios.get("/users");
                const rolesResponse = await axios.get("/roles");
                setUsers(usersResponse.data);
                setRoles(rolesResponse.data);
            } catch (err) {
                console.error("Ошибка загрузки данных:", err);
                setError("Не удалось загрузить пользователей и роли.");
            }
        };

        fetchUsersAndRoles();
    }, []);

    const removeRoleFromUser = async (userId, roleName) => {
        try {
            await axios.delete(`/users/${userId}/role`, { data: { name: roleName } });
            alert("Роль удалена!");
            setUsers((prevUsers) =>
                prevUsers.map((user) =>
                    user.id === userId
                        ? {
                            ...user,
                            roles: user.roles.filter((role) => role.name !== roleName),
                        }
                        : user
                )
            );
        } catch (err) {
            console.error("Ошибка удаления роли:", err);
            setError("Не удалось удалить роль.");
        }
    };

    const assignRoleToUser = async (userId, roleName) => {
        try {
            await axios.post(`/users/${userId}/role`, { name: roleName });
            alert("Роль назначена!");
            setUsers((prevUsers) =>
                prevUsers.map((user) =>
                    user.id === userId
                        ? {
                            ...user,
                            roles: [...user.roles, { id: Date.now(), name: roleName }],
                        }
                        : user
                )
            );
        } catch (err) {
            console.error("Ошибка назначения роли:", err);
            setError("Не удалось назначить роль.");
        }
    };

    return (
        <div>
            <h1>Управление ролями пользователей</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {users.map((user) => (
                <div key={user.id} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
                    <h2>Пользователь: {user.email}</h2>
                    <h3>Назначенные роли:</h3>
                    <ul>
                        {user.roles.map((role) => (
                            <li key={role.id}>
                                {role.name}
                                <button onClick={() => removeRoleFromUser(user.id, role.name)}>Удалить</button>
                            </li>
                        ))}
                    </ul>
                    <div>
                        <h4>Добавить роль:</h4>
                        <select
                            onChange={(e) => assignRoleToUser(user.id, e.target.value)}
                            defaultValue=""
                        >
                            <option value="" disabled>
                                Выберите роль
                            </option>
                            {roles.map((role) => (
                                <option key={role.id} value={role.name}>
                                    {role.name}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ManageUserRoles;
