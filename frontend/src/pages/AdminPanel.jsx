import {Link, useNavigate} from "react-router-dom";
import PageHeader from "../components/PageHeader";

const AdminPanel = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    };

    return (
        <div className="admin-panel">
            <PageHeader title="Административная панель" />
            <nav>
                <ul>
                    <li>
                        <Link to="/admin/models">Управление моделями</Link>
                    </li>
                    <li>
                        <Link to="/admin/users">Управление пользователями</Link>
                    </li>
                    <li>
                        <Link to="/admin/categories">Управление категориями</Link>
                    </li>
                    <li>
                        <Link to="/admin/roles">Управление ролями</Link>
                    </li>
                    <li>
                        <Link to="/admin/user-roles">Управление ролями пользователей</Link>
                    </li>
                </ul>
            </nav>
            <button onClick={handleLogout} style={{ marginTop: "20px", padding: "10px 20px" }}>
                Выйти
            </button>
        </div>
    );
};

export default AdminPanel;
