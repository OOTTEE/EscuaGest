import {useHttpClient} from "../../infrastructure/http/http";

export const Dashboard = () => {

    const httpClient = useHttpClient();
    httpClient.get("http://localhost:8080/swimmer")
        .then(value => console.log(value))


    return (
        <div className="dashboard">
            <h2>Dashboard</h2>
        </div>
    )
}
