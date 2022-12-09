import {Competition} from "../competition/Competition";
import {FloatButton, List} from "antd";
import {PlusOutlined} from "@ant-design/icons";
import {useNavigate} from "react-router-dom";

export const CompetitionDashboard = () => {
    const numbers = [1, 2, 3, 4, 5, 6, 7, 8]
    const navigate = useNavigate();
    let cards = numbers.map(value => {
        return <Competition competition_id={value}></Competition>
    })

    return (
        <>
            <List grid={{
                xs: 1, sm: 1, md: 2, lg: 3, xl: 4, xxl: 6
            }}
                  dataSource={cards}
                  renderItem={item => (
                      <List.Item style={{paddingLeft: 8, paddingRight: 8}}>
                          {item}
                      </List.Item>
                  )}
            />
            <FloatButton
                type={"primary"}
                tooltip={<div>Nueva Cometicion</div>}
                icon={<PlusOutlined/>}
                onClick={() => {
                    navigate('/competitions/new')
                }}
            />
        </>
    )
}
