import {Layout} from "antd";

type CompetitionProps = {
    competition_id: number,
    data?: any
}

export const Competition = ({competition_id}:CompetitionProps) => {
    return (
        <Layout>
            <h2>{competition_id}</h2>
        </Layout>
    )
}