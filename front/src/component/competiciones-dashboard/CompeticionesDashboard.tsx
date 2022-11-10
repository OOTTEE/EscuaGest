import {Competition} from "../competition/Competition";
import {List} from "antd";

export const CompeticionesDashboard = () => {
    const numbers = [1, 2, 3, 4, 5, 6, 7, 8]

    let cards = numbers.map(value => {
        return <Competition competition_id={value} style={{width: '100%'}}></Competition>
    })

    return (
        <>
            <List grid={{
                gutter: 16,
                xs: 1, sm: 1, md: 2, lg: 3, xl: 4, xxl: 6
            }}
                  dataSource={cards}
                  renderItem={item => (
                      <List.Item>
                          {item}
                      </List.Item>
                  )}
            />
        </>
    )
}
