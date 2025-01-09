import React, { useEffect, useState } from "react";
import axios from "../api/httpClient";

const ManageRoles = () => {
    const [roles, setRoles] = useState([]);
    const [permissions, setPermissions] = useState([]);
    const [newRoleName, setNewRoleName] = useState("");
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchRolesAndPermissions = async () => {
            try {
                const rolesResponse = await axios.get("/roles");
                const permissionsResponse = await axios.get("/permissions");
                setRoles(rolesResponse.data);
                setPermissions(permissionsResponse.data);
            } catch (err) {
                console.error("Ошибка загрузки данных:", err);
                setError("Не удалось загрузить роли и разрешения.");
            }
        };

        fetchRolesAndPermissions();
    }, []);

    const addRole = async () => {
        try {
            if (!newRoleName.trim()) {
                alert("Введите название роли!");
                return;
            }
            const response = await axios.post("/roles", { name: newRoleName });
            setRoles([...roles, response.data]);
            setNewRoleName(""); // Сбрасываем поле ввода
            alert("Роль добавлена!");
        } catch (err) {
            console.error("Ошибка добавления роли:", err);
            setError("Не удалось добавить роль.");
        }
    };

    const addPermissionToRole = async (roleId, permissionName) => {
        try {
            await axios.post(`/roles/${roleId}/permissions`, { permissionName });
            alert("Разрешение добавлено!");
        } catch (err) {
            console.error("Ошибка добавления разрешения:", err);
            setError("Не удалось добавить разрешение.");
        }
    };

    const removePermissionFromRole = async (roleId, permissionId) => {
        try {
            await axios.delete(`/roles/${roleId}/permissions/${permissionId}`);
            alert("Разрешение удалено!");
        } catch (err) {
            console.error("Ошибка удаления разрешения:", err);
            setError("Не удалось удалить разрешение.");
        }
    };

    return (
        <div>
            <h1>Управление ролями</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <div>
                <h2>Добавить новую роль</h2>
                <input
                    type="text"
                    value={newRoleName}
                    onChange={(e) => setNewRoleName(e.target.value)}
                    placeholder="Название роли"
                />
                <button onClick={addRole}>Добавить роль</button>
            </div>
            {roles.map((role) => (
                <div key={role.id} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
                    <h2>Роль: {role.name}</h2>
                    <h3>Разрешения:</h3>
                    <ul>
                        {role.permissions.map((permission) => (
                            <li key={permission.id}>
                                {permission.name}{" "}
                                <button onClick={() => removePermissionFromRole(role.id, permission.id)}>
                                    Удалить
                                </button>
                            </li>
                        ))}
                    </ul>
                    <div>
                        <h4>Добавить разрешение:</h4>
                        <select
                            onChange={(e) => addPermissionToRole(role.id, e.target.value)}
                            defaultValue=""
                        >
                            <option value="" disabled>
                                Выберите разрешение
                            </option>
                            {permissions.map((permission) => (
                                <option key={permission.id} value={permission.name}>
                                    {permission.name}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ManageRoles;
