#!/usr/bin/env python3
"""
添加新题目的辅助工具
用法：python scripts/add_problem.py
"""

import json
import sys
from datetime import datetime
from pathlib import Path

def add_problem():
    """交互式添加新题目"""
    data_file = Path(__file__).parent.parent / 'leetcode_data.json'

    if not data_file.exists():
        print("错误：找不到 leetcode_data.json")
        return

    with open(data_file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    print("📝 添加新题目")
    print("-" * 40)

    try:
        problem_id = int(input("题号: ").strip())

        # 检查是否已存在
        existing_ids = [p['id'] for p in data['problems']]
        if problem_id in existing_ids:
            print(f"⚠️  题号 {problem_id} 已存在，将更新记录")
            # 这里可以添加更新逻辑

        title = input("题目名称: ").strip()
        title_slug = input("题目slug（英文标识）: ").strip()

        print("\n难度选项：")
        print("1. 简单")
        print("2. 中等")
        print("3. 困难")
        difficulty_choice = input("选择难度 (1/2/3): ").strip()
        difficulty_map = {"1": "简单", "2": "中等", "3": "困难"}
        difficulty = difficulty_map.get(difficulty_choice, "中等")

        print("\n输入标签（用逗号分隔，如：数组,哈希表）:")
        tags_input = input("标签: ").strip()
        tags = [tag.strip() for tag in tags_input.split(',') if tag.strip()]

        print("\n解决日期（留空则使用今天）:")
        date_input = input("日期 (YYYY-MM-DD): ").strip()
        solved_dates = [date_input] if date_input else [datetime.now().strftime('%Y-%m-%d')]

        # 创建新题目对象
        new_problem = {
            "id": problem_id,
            "title": title,
            "title_slug": title_slug,
            "difficulty": difficulty,
            "tags": tags,
            "solved_dates": solved_dates,
            "leetcode_url": f"https://leetcode.cn/problems/{title_slug}/"
        }

        # 添加到数据
        data['problems'].append(new_problem)
        data['total_solved'] = len(data['problems'])
        data['updated_at'] = datetime.now().strftime('%Y-%m-%d')

        # 保存数据
        with open(data_file, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)

        print(f"\n✅ 成功添加题目：{title} (No.{problem_id})")
        print(f"   难度：{difficulty}")
        print(f"   标签：{', '.join(tags)}")
        print(f"   解决日期：{solved_dates[0]}")

        # 询问是否更新README
        update = input("\n是否立即更新README？ (y/n): ").strip().lower()
        if update == 'y':
            from update_readme import LeetCodeREADME
            generator = LeetCodeREADME(str(data_file))
            generator.update_readme()

    except KeyboardInterrupt:
        print("\n❌ 已取消")
    except Exception as e:
        print(f"❌ 发生错误：{e}")

if __name__ == "__main__":
    add_problem()