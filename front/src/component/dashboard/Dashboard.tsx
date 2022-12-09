import {useHttpClient} from "../../infrastructure/http/http";
import {useEffect} from "react";

export const Dashboard = () => {
    const httpClient = useHttpClient();
    //
    // useEffect(() => {
    //     httpClient.get("http://localhost:8080/swimmer")
    //         .then(value => console.log(value))
    // }, [])

    useEffect(() => {
    },[])


    return (
        <div className="dashboard">
            <h2>Dashboard</h2>
        </div>
    )
}
